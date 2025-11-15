package taskawaii.components;

import com.github.lgooddatepicker.components.DatePickerSettings.DateArea;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import raven.toast.Notifications;
import taskawaii.control.Priority;
import taskawaii.control.Constants;
import taskawaii.control.TASKawaii;
import taskawaii.control.Task;

/**
 *This is the JPanel that enables users to edit their tasks.
 * @author Raphael
 */
public class TaskEditorPanel extends JPanel{

    /**
     *The constructor takes in a task to reference.
     * @param task
     */
    TaskEditorPanel(Task task){
        initComponents(task);
    }
    
    /**
     *This initializes the components of the JPanel.
     * @param task task to be edited
     */
    public void initComponents(Task task){
        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        titlePanel.setPreferredSize(new Dimension(1040, 60));
        
        taskTitleField = new HintTextField("Enter title");
        taskTitleField.setFont(Constants.font.deriveFont((float) 18));
        taskTitleField.setBorder(new EmptyBorder(10, 10, 10, 10));
        try{
            taskTitleField.setText(task.getTitle());
        } catch(NullPointerException e){
            taskTitleField.setEnabled(false);
        }
        
        titleScrollPane = new JScrollPane(taskTitleField);
        titleScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        titleScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        titleScrollPane.setPreferredSize(new Dimension(450,40));
        titleScrollPane.setBorder(BorderFactory.createLineBorder(UIManager.getColor("Button.borderColor")));
        titlePanel.add(titleScrollPane);
        
        String priorities[] = {"Low", "Medium", "High", "IMMEDIATE"};
        prioritySelector = new JComboBox(priorities);
        prioritySelector.setFont(Constants.font.deriveFont((float) 16));
        prioritySelector.setPreferredSize(new Dimension(150, 45));
        prioritySelector.setEditable(true);
        prioritySelector.setBackground(UIManager.getColor("ComboBox.background"));
        prioritySelector.setForeground(UIManager.getColor("ComboBox.foreground"));
        try{
            if(task.getPriority() != null)
                prioritySelector.setSelectedItem(task.getPriority().getPriorityName());
        } catch(NullPointerException e){
            prioritySelector.setEnabled(false);
        }
        titlePanel.add(prioritySelector);
        
        dateSelector = new DateTimePicker();
        dateSelector.getDatePicker().getSettings().setColorBackgroundWeekNumberLabels(UIManager.getColor("Button.background"), true);
        dateSelector.getDatePicker().getSettings().setColorBackgroundWeekdayLabels(UIManager.getColor("Button.background"), true);
        dateSelector.getDatePicker().getComponentDateTextField().setEditable(false);
        dateSelector.getDatePicker().getSettings().setColor(DateArea.BackgroundCalendarPanelLabelsOnHover, UIManager.getColor("Button.default.startBackground"));
        dateSelector.getDatePicker().getSettings().setColor(DateArea.BackgroundOverallCalendarPanel, UIManager.getColor("Button.background"));
        dateSelector.getDatePicker().getSettings().setColor(DateArea.BackgroundMonthAndYearMenuLabels, UIManager.getColor("Button.background"));
        dateSelector.getDatePicker().getSettings().setColor(DateArea.CalendarTextWeekdays, UIManager.getColor("Button.foreground"));
        dateSelector.getDatePicker().getSettings().setColor(DateArea.BackgroundTodayLabel, UIManager.getColor("Button.background"));
        dateSelector.getDatePicker().getSettings().setColor(DateArea.BackgroundTopLeftLabelAboveWeekNumbers, UIManager.getColor("Button.background"));
        dateSelector.getDatePicker().getSettings().setColor(DateArea.BackgroundClearLabel, UIManager.getColor("Button.background"));
        dateSelector.getDatePicker().getSettings().setColor(DateArea.CalendarBackgroundSelectedDate, UIManager.getColor("Button.background"));
        dateSelector.getDatePicker().getSettings().setColor(DateArea.CalendarBackgroundNormalDates, UIManager.getColor("Button.startBackground"));
        dateSelector.getDatePicker().getSettings().setColor(DateArea.CalendarTextNormalDates, UIManager.getColor("Button.foreground"));
        dateSelector.getDatePicker().getSettings().setColor(DateArea.CalendarBorderSelectedDate, UIManager.getColor("Button.default.background"));
        dateSelector.getDatePicker().getSettings().setColor(DateArea.TextFieldBackgroundInvalidDate, UIManager.getColor("TextField.background"));
        dateSelector.getDatePicker().getSettings().setColor(DateArea.TextFieldBackgroundValidDate, UIManager.getColor("TextField.background"));
        dateSelector.getDatePicker().getSettings().setColor(DateArea.TextFieldBackgroundDisabled, UIManager.getColor("TextField.disabledBackground"));
        dateSelector.getDatePicker().getSettings().setColor(DateArea.DatePickerTextValidDate, UIManager.getColor("Button.foreground"));
        dateSelector.getDatePicker().getSettings().setColor(DateArea.DatePickerTextInvalidDate, UIManager.getColor("Button.foreground"));
        dateSelector.getDatePicker().getComponentDateTextField().setBorder(BorderFactory.createLineBorder(UIManager.getColor("Button.borderColor")));
        
        dateSelector.getTimePicker().getSettings().setColor(TimeArea.TextFieldBackgroundDisabled, UIManager.getColor("TextField.disabledBackground"));
        dateSelector.getTimePicker().getSettings().setColor(TimeArea.TextFieldBackgroundInvalidTime, UIManager.getColor("TextField.background"));
        dateSelector.getTimePicker().getSettings().setColor(TimeArea.TextFieldBackgroundValidTime, UIManager.getColor("TextField.background"));
        dateSelector.getTimePicker().getSettings().setColor(TimeArea.TimePickerTextValidTime, UIManager.getColor("TextField.foreground"));
        dateSelector.getTimePicker().getSettings().setColor(TimeArea.TimePickerTextInvalidTime, UIManager.getColor("TextField.foreground"));
        dateSelector.getTimePicker().getComponentTimeTextField().setBorder(BorderFactory.createLineBorder(UIManager.getColor("Button.borderColor")));
        
        dateSelector.setPreferredSize(new Dimension(300, 40));
        titlePanel.add(dateSelector);
        try{
            dateSelector.setDateTimeStrict(task.getEndDateTime());
        } catch(NullPointerException e){
            dateSelector.setEnabled(false);
        }
        
        saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(100, 40));
        saveButton.setFont(Constants.font.deriveFont((float) 16));
        if(task == null)
            saveButton.setEnabled(false);
        saveButton.addActionListener((e) -> {
            if(task != null){
                Boolean validity = true;
                String taskTitle = taskTitleField.getText();
                if(taskTitle.isBlank()){
                    titleScrollPane.setBorder(BorderFactory.createLineBorder(Constants.errorColor, 4, true));
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Invalid title input.");
                    validity = false;
                } else
                    titleScrollPane.setBorder(null);
                String taskContent = bodyPanel.getTextArea().getText();
                if(taskContent == null || taskContent.isEmpty()){
                    bodyPanel.getScrollPane().setBorder(BorderFactory.createLineBorder(Constants.errorColor, 4, true));
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Invalid content input.");
                    validity = false;
                } else
                    bodyPanel.getScrollPane().setBorder(null);
                Priority taskPriority = Priority.toPriority((String) prioritySelector.getSelectedItem());
                if(taskPriority == null){
                    prioritySelector.setBorder(BorderFactory.createLineBorder(Constants.errorColor, 4, true));
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Invalid priority input.");
                    validity = false;
                } else
                    prioritySelector.setBorder(null);
                LocalDateTime taskDate = dateSelector.getDateTimeStrict();
                if(taskDate == null){
                    dateSelector.setBorder(BorderFactory.createLineBorder(Constants.errorColor, 4, true));
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Invalid date input.");
                    validity = false;
                } else
                    dateSelector.setBorder(null);
                String fontName = (String) bodyPanel.getFontSelector().getSelectedItem();
                List fontNameList = Arrays.asList(TaskEditorPanelBody.systemFonts);
                int fontSize = (int) bodyPanel.getFontSizeSelector().getValue();
                if(fontName == null || !fontNameList.contains(fontName)){
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Invalid font.");
                    validity = false;
                }
                if(validity){
                    task.setTitle(taskTitle);
                    task.setContent(taskContent);
                    task.setPriority(taskPriority);
                    task.setEndDateTime(taskDate);
                    task.setFont(new Font(fontName, Font.PLAIN, fontSize));
                    ((PendingTasksBar) TASKawaii.getHomePage().getWestPanel()).refreshPendingTasksBar();
                    try{
                        if(TASKawaii.getHomePage().getCalendarFrame() != null){
                            TASKawaii.getHomePage().getCalendarFrame().refreshCalendar();
                        }
                    } catch(NullPointerException x){}
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Task saved.");
                }
            }
        });
        titlePanel.add(saveButton);
        titlePanel.revalidate();
        optionScrollPane = new JScrollPane(titlePanel);
        optionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        optionScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
        optionScrollPane.setBorder(null);
        
        bodyPanel = new TaskEditorPanelBody();
        bodyPanel.setPreferredSize(new Dimension(100, 100));
        try{
            bodyPanel.getTextArea().setText(task.getContent());
        } catch(NullPointerException e){
            bodyPanel.getTextArea().setEnabled(false);
        }
        try{
            bodyPanel.getFontSelector().setSelectedItem(task.getFont().getFamily());
            bodyPanel.getFontSizeSelector().setValue(task.getFont().getSize());
        } catch(NullPointerException e){
            bodyPanel.getFontSelector().setEnabled(false);
            bodyPanel.getFontSizeSelector().setEnabled(false);
        }
        
        
        setLayout(new BorderLayout());
        add(optionScrollPane, BorderLayout.NORTH);
        add(bodyPanel, BorderLayout.CENTER);
    }
    
    /**
     *This refreshes the TaskEditorPanel for any changes in the wanted task to edit.
     * @param task the new task to be edited
     */
    public void refreshTaskEditorPanel(Task task){
        removeAll();
        initComponents(task);
        revalidate();
        repaint();
    }
    
    /**
     *This is the JPanel that will hold the title text field.
     */
    private JPanel titlePanel;

    /**
     *This is the JPanel that will hold the content HintTextArea and other actions relevant to it.
     */
    private TaskEditorPanelBody bodyPanel;

    /**
     *This is the HintTextField for the title.
     */
    private HintTextField taskTitleField;

    /**
     *This is the JScrollPane for the taskTitleField.
     */
    private JScrollPane titleScrollPane;

    /**
     *This is the JScrollPane that will hold the general options for the task.
     */
    private JScrollPane optionScrollPane;

    /**
     *This is the JComboBox to select the priority.
     */
    private JComboBox prioritySelector;

    /**
     *This is the DateTimePicker to select the date.
     */
    private DateTimePicker dateSelector;

    /**
     *This is the JButton to save the task.
     */
    private JButton saveButton;
}