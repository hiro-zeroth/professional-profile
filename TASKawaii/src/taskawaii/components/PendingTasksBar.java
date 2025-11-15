package taskawaii.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import raven.toast.Notifications;
import taskawaii.control.Constants;
import taskawaii.control.TASKawaii;
import taskawaii.control.Task;
import taskawaii.control.TaskList;

/**
 *This is the PendingTasksBar seen in the eastPanel of the HomePage.
 * @author Raphael
 */
public class PendingTasksBar extends JPanel implements MouseListener{

    /**
     *The constructor will take in a TaskList that it will reference as the overallTaskList.
     * @param taskList
     */
    PendingTasksBar(TaskList taskList){
        overallTaskList = taskList;
        refreshPendingTasks(taskList, 0);
        initComponents();
    }
    
    /**
     *This will initialize components for the JPanel.
     */
    private void initComponents(){
        actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout());
        listActions = new JPanel();
        listActions.setLayout(new GridLayout(1, 3));
        sortActions = new JPanel();
        sortActions.setLayout(new GridLayout(2, 2));
        listActions.setBorder(new EmptyBorder(10, 10, 10, 10));
        sortActions.setBorder(new EmptyBorder(10, 10, 10, 10));
        sortActions.setPreferredSize(new Dimension((int) (Constants.WX * 0.30), (int) (Constants.WY * 0.09)));
        actionPanel.setPreferredSize(new Dimension(Constants.WX, (int) (Constants.WY * 0.09)));
        
        ImageIcon addIcon = new ImageIcon("AddIcon.png");
        addTaskButton = new JButton();
        addTaskButton.setIcon(new ImageIcon(addIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        addTaskButton.addActionListener(e -> {
            Task newTask = new Task();
            overallTaskList.add(newTask);
            selectedTask = newTask;
            refreshPendingTasks(overallTaskList, sortMode);
            ((TaskEditorPanel) TASKawaii.getHomePage().getCenterPanel()).refreshTaskEditorPanel(newTask);
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Task added.");
        });
        listActions.add(addTaskButton);
        
        ImageIcon removeIcon = new ImageIcon("RemoveIcon.png");
        removeTaskButton = new JButton();
        removeTaskButton.setIcon(new ImageIcon(removeIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        removeTaskButton.addActionListener(e -> {
            if(selectedTask != null){
                int i = pendingTasks.indexOf(selectedTask);
                overallTaskList.remove(selectedTask);
                refreshPendingTasks(overallTaskList, sortMode);
                if(pendingTasks.isEmpty()){
                    selectedTask = null;
                }
                else if (i - 1 >= 0){
                    selectedTask = pendingTasks.get(i - 1);
                }
                else
                    selectedTask = pendingTasks.get(i);
                refreshPendingTasks(overallTaskList, sortMode);
                ((TaskEditorPanel) TASKawaii.getHomePage().getCenterPanel()).refreshTaskEditorPanel(selectedTask);
                Notifications.getInstance().show(Notifications.Type.INFO, "Task removed.");
            } else
                Notifications.getInstance().show(Notifications.Type.WARNING, "No task selected.");
        });
        listActions.add(removeTaskButton);
        
        ImageIcon markIcon = new ImageIcon("CompleteIcon.png");
        markTaskButton = new JButton();
        markTaskButton.setIcon(new ImageIcon(markIcon.getImage().getScaledInstance(30, 28, Image.SCALE_SMOOTH)));
        markTaskButton.addActionListener(e -> {
            if(selectedTask != null){
                int i = pendingTasks.indexOf(selectedTask);
                selectedTask.markCompleted();
                refreshPendingTasks(overallTaskList, sortMode);
                if(pendingTasks.isEmpty()){
                    selectedTask = null;
                }
                else if (i - 1 >= 0){
                    selectedTask = pendingTasks.get(i - 1);
                }
                else
                    selectedTask = pendingTasks.get(i);
                ((TaskEditorPanel) TASKawaii.getHomePage().getCenterPanel()).refreshTaskEditorPanel(selectedTask);
                ((CompletedTasksBar) TASKawaii.getHomePage().getEastPanel()).refreshCompletedTasksBar();
                setupList();
                Notifications.getInstance().show(Notifications.Type.SUCCESS, "Task marked as complete.");
            } else
                Notifications.getInstance().show(Notifications.Type.WARNING, "No task selected.");
        });
        listActions.add(markTaskButton);
        
        ImageIcon sortByAdditionIcon = new ImageIcon("SortByAddition.png");
        sortByAddition = new JButton();
        sortByAddition.setIcon(new ImageIcon(sortByAdditionIcon.getImage().getScaledInstance(40, 50, Image.SCALE_SMOOTH)));
        sortByAddition.setPreferredSize(new Dimension(30, 30));
        sortByAddition.addActionListener(e -> {
            sortMode = 0;
            refreshPendingTasks(overallTaskList, sortMode);
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Sorted by addition.");
        });
        sortActions.add(sortByAddition);
        
        ImageIcon sortByEndDateIcon = new ImageIcon("SortByDeadlineIcon.png");
        sortByEndDate = new JButton();
        sortByEndDate.setIcon(new ImageIcon(sortByEndDateIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        sortByEndDate.setPreferredSize(new Dimension(30, 30));
        sortByEndDate.addActionListener(e -> {
            sortMode = 1;
            refreshPendingTasks(overallTaskList, sortMode);
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Sorted by deadline.");
        });
        sortActions.add(sortByEndDate);
        
        ImageIcon sortByPriorityIcon = new ImageIcon("SortByPriorityIcon.png");
        sortByPriority = new JButton();
        sortByPriority.setIcon(new ImageIcon(sortByPriorityIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        sortByPriority.setPreferredSize(new Dimension(30, 30));
        sortByPriority.addActionListener(e -> {
            sortMode = 2;
            refreshPendingTasks(overallTaskList, sortMode);
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Sorted by priority.");
        });
        sortActions.add(sortByPriority);
        
        ImageIcon sortByNameIcon = new ImageIcon("SortByNameIcon.png");
        sortByName = new JButton();
        sortByName.setIcon(new ImageIcon(sortByNameIcon.getImage().getScaledInstance(35, 50, Image.SCALE_SMOOTH)));
        sortByName.setPreferredSize(new Dimension(30, 30));
        sortByName.addActionListener(e -> {
            sortMode = 3;
            refreshPendingTasks(overallTaskList, sortMode);
                Notifications.getInstance().show(Notifications.Type.SUCCESS, "Sorted by name.");
        });
        sortActions.add(sortByName);
        
        actionPanel.add(listActions, BorderLayout.CENTER);
        actionPanel.add(sortActions, BorderLayout.EAST);
        
        listPanel = new JPanel();
        setupList();
        listPane = new JScrollPane(listPanel);
        listPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        listPane.getVerticalScrollBar().setUnitIncrement(10);

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());
        add(actionPanel, BorderLayout.NORTH);
        add(listPane, BorderLayout.CENTER);
    }
    
    /**
     *This will set up the list of pending tasks.
     */
    private void setupList(){
        if(listPanel != null){
            listPanel.removeAll();
            listPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
            for(Task task : pendingTasks){
                PendingTaskListItem listItem = new PendingTaskListItem(task);
                listItem.addMouseListener(this);
                if(task.equals(selectedTask)){
                    listItem.setBorder(BorderFactory.createLineBorder(UIManager.getColor("Button.default.focusedBorderColor"), 4));
                }
                if(task.isDue())
                    listItem.setBackground(Constants.errorColor);
                listPanel.add(listItem);
            }
            listPanel.setPreferredSize(new Dimension(250, pendingTasks.size()*80));
            listPanel.repaint();
            listPanel.revalidate();
        }
    }
    
    /**
     *This will refresh the refresh the JPanel usually in reference to the
     * overallTaskList. It will also refresh any relevant component that may be
     * affected in its change.
     * @param taskList TaskList used for basis for the pendingTasks
     * @param mode sort mode in integer form
     */
    private void refreshPendingTasks(TaskList taskList, int mode){
        pendingTasks.clear();
        for(Task task : taskList){
            if(!task.isComplete())
                pendingTasks.add(task);
        }
        switch(mode){
            case 1 -> pendingTasks.sortByEndDate();
            case 2 -> pendingTasks.sortByPriority();
            case 3 -> pendingTasks.sortByName();
        }
        setupList();
        try{
            if(TASKawaii.getHomePage().getCalendarFrame() != null){
                TASKawaii.getHomePage().getCalendarFrame().refreshCalendar();
            }
        } catch(NullPointerException e){}
    }
    
    /**
     *This will refresh the PendingTasksBar.
     * <p>
     * This is mainly used to refresh the CompletedTasksBar outside of the class itself and
     * preserves the sortMode currently selected.
     */
    public void refreshPendingTasksBar(){
        synchronized(this){
            refreshPendingTasks(overallTaskList, sortMode);
        }
    }
    
    /**
     *This is the general JPanel that stores all the other action JPanels.
     */
    private JPanel actionPanel;

    /**
     *This is the JPanel that stores all the actions the user can do on the list.
     */
    private JPanel listActions;

    /**
     *This is the JPanel that stores all the actions the user can do to sort the list.
     */
    private JPanel sortActions;

    /**
     *This is the JPanel that stores the listItems.
     */
    private JPanel listPanel;

    /**
     *This is the JScrollPanel that will store the listPanel to make it scrollable.
     */
    private JScrollPane listPane;

    /**
     *This is the JButton to add tasks.
     */
    private JButton addTaskButton;

    /**
     *This is the JButton to remove tasks.
     */
    private JButton removeTaskButton;

    /**
     *This is the JButton to mark tasks as incomplete.
     */
    private JButton markTaskButton;

    /**
     *This is the JButton to sort by addition.
     */
    private JButton sortByAddition;

    /**
     *This is the JButton to sort by endDate.
     */
    private JButton sortByEndDate;

    /**
     *This is the JButton to sort by priority.
     */
    private JButton sortByPriority;

    /**
     *This is the JButton to sort by name.
     */
    private JButton sortByName;

    /**
     *This is the PendingTasksBar's own copy of the overallTaskList that
     * stores only the incomplete tasks.
     */
    private TaskList pendingTasks = new TaskList();

    /**
     *This is the reference of the PendingTasksBar for the user TaskList.
     */
    private TaskList overallTaskList;

     /**
     *This is the current task selected by the user. Its value can be null.
     */
    private Task selectedTask;

    /**
     *This is the sort mode the PendingTasksBar is currently in.
     * <p>
     * (0 - addition, 1 - endDate, 2 - priority, 3 - name)
     */
    private int sortMode = 0;

    /**
     *This is the method if a listItem is clicked.
     * @param e MouseEvent that happened
     */
    @Override
    public void mouseClicked(MouseEvent e) {}

    /**
     *This is the method if a listItem is pressed. It will set selectedTask
     * to the task the listItem is referencing. If the tasks are the same, the
     * selectedTask will be set to null.
     * @param e MouseEvent that happened
     */
    @Override
    public void mousePressed(MouseEvent e) {
        PendingTaskListItem item = (PendingTaskListItem) e.getSource();
        if(selectedTask == null || !item.getTask().equals(selectedTask)){
            selectedTask = item.getTask();
            ((TaskEditorPanel) TASKawaii.getHomePage().getCenterPanel()).refreshTaskEditorPanel(item.getTask());
        }
        else{
            selectedTask = null;
            ((TaskEditorPanel) TASKawaii.getHomePage().getCenterPanel()).refreshTaskEditorPanel(null);
        }
        setupList();
    }

    /**
     *This is the method if a listItem is released.
     * @param e MouseEvent that happened
     */
    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
     *This is the method if the mouse enters a listItem's area. It will set the appropriate
     * border that serves to highlight the listItem.
     * @param e MouseEvent that happened
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        PendingTaskListItem item = (PendingTaskListItem) e.getSource();
        item.setBorder(BorderFactory.createLineBorder(UIManager.getColor("Button.default.focusedBorderColor"), 4, false));
    }

    /**
     *This is the method if the mouse exits the listItem's area. It will clear the listItem's
     * border unless it is the selectedTask.
     * @param e MouseEvent that happened
     */
    @Override
    public void mouseExited(MouseEvent e) {
        PendingTaskListItem item = (PendingTaskListItem) e.getSource();
        if(!item.getTask().equals(selectedTask))
            item.setBorder(null);
    }
}
