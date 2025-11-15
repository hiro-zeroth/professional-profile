package taskawaii.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import taskawaii.control.TASKawaii;
import taskawaii.control.Task;
import taskawaii.control.TaskList;

/**
 *This is the CompletedTasksBar seen in the eastPanel of the HomePage.
 * @author Raphael
 */
public class CompletedTasksBar extends JPanel implements MouseListener{

    /**
     *The constructor will take in a TaskList that it will reference as the overallTaskList.
     * @param taskList
     */
    CompletedTasksBar(TaskList taskList){
        overallTaskList = taskList;
        refreshCompletedTasks(taskList, 0);
        initComponents();
    }
    
    /**
     *This will initialize components for the JPanel.
     */
    private void initComponents(){
        actionPanel = new JPanel();
        actionPanel.setLayout(new BorderLayout());
        listActions = new JPanel();
        listActions.setLayout(new FlowLayout(FlowLayout.TRAILING));
        sortActions = new JPanel();
        sortActions.setLayout(new FlowLayout());
        listActions.setBorder(new EmptyBorder(10, 10, 10, 10));
        sortActions.setBorder(new EmptyBorder(10, 10, 10, 10));
        sortActions.setPreferredSize(new Dimension(90, 90));
        actionPanel.setPreferredSize(new Dimension(250, 90));
        
        ImageIcon removeIcon = new ImageIcon("removeIcon.png");
        removeTaskButton = new JButton();
        removeTaskButton.setPreferredSize(new Dimension(50,30));
        removeTaskButton.setIcon(new ImageIcon(removeIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        removeTaskButton.addActionListener(e -> {
            if(selectedTask != null){
                int i = completedTasks.indexOf(selectedTask);
                overallTaskList.remove(selectedTask);
                refreshCompletedTasks(overallTaskList, sortMode);
                if(completedTasks.isEmpty()){
                    selectedTask = null;
                }
                else if (i - 1 >= 0){
                    selectedTask = completedTasks.get(i - 1);
                }
                else
                    selectedTask = completedTasks.get(i);
                refreshCompletedTasks(overallTaskList, sortMode);
                Notifications.getInstance().show(Notifications.Type.INFO, "Task removed.");
            } else
                Notifications.getInstance().show(Notifications.Type.SUCCESS, "No task selected.");
        });
        listActions.add(removeTaskButton);
        
        ImageIcon markIcon = new ImageIcon("IncompleteIcon.png");
        markTaskButton = new JButton();
        markTaskButton.setPreferredSize(new Dimension(50, 30));
        markTaskButton.setIcon(new ImageIcon(markIcon.getImage().getScaledInstance(29, 26, Image.SCALE_SMOOTH)));
        markTaskButton.addActionListener(e -> {
            if(selectedTask != null){
                int i = completedTasks.indexOf(selectedTask);
                selectedTask.markNotCompleted();
                refreshCompletedTasks(overallTaskList, sortMode);
                if(completedTasks.isEmpty()){
                    selectedTask = null;
                }
                else if (i - 1 >= 0){
                    selectedTask = completedTasks.get(i - 1);
                }
                else
                    selectedTask = completedTasks.get(i);
                ((PendingTasksBar) TASKawaii.getHomePage().getWestPanel()).refreshPendingTasksBar();
                Notifications.getInstance().show(Notifications.Type.INFO, "Task marked as incomplete.");
            } else
                Notifications.getInstance().show(Notifications.Type.SUCCESS, "No task selected.");
        });
        listActions.add(markTaskButton);
        
        ImageIcon sortByAdditionIcon = new ImageIcon("SortByAddition.png");
        sortByAddition = new JButton();
        sortByAddition.setIcon(new ImageIcon(sortByAdditionIcon.getImage().getScaledInstance(40, 50, Image.SCALE_SMOOTH)));
        sortByAddition.setPreferredSize(new Dimension(30, 30));
        sortByAddition.addActionListener(e -> {
            sortMode = 0;
            refreshCompletedTasks(overallTaskList, sortMode);
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Sorted by addition.");
        });
        sortActions.add(sortByAddition);
        
        ImageIcon sortByEndDateIcon = new ImageIcon("SortByDeadlineIcon.png");
        sortByEndDate = new JButton();
        sortByEndDate.setIcon(new ImageIcon(sortByEndDateIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        sortByEndDate.setPreferredSize(new Dimension(30, 30));
        sortByEndDate.addActionListener(e -> {
            sortMode = 1;
            refreshCompletedTasks(overallTaskList, sortMode);
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Sorted by deadline.");
        });
        sortActions.add(sortByEndDate);
        
        ImageIcon sortByPriorityIcon = new ImageIcon("SortByPriorityIcon.png");
        sortByPriority = new JButton();
        sortByPriority.setIcon(new ImageIcon(sortByPriorityIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        sortByPriority.setPreferredSize(new Dimension(30, 30));
        sortByPriority.addActionListener(e -> {
            sortMode = 2;
            refreshCompletedTasks(overallTaskList, sortMode);
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Sorted by priority.");
        });
        sortActions.add(sortByPriority);
        
        ImageIcon sortByNameIcon = new ImageIcon("SortByNameIcon.png");
        sortByName = new JButton();
        sortByName.setIcon(new ImageIcon(sortByNameIcon.getImage().getScaledInstance(35, 50, Image.SCALE_SMOOTH)));
        sortByName.setPreferredSize(new Dimension(30, 30));
        sortByName.addActionListener(e -> {
            sortMode = 3;
            refreshCompletedTasks(overallTaskList, sortMode);
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
     *This will refresh the refresh the JPanel usually in reference to the
     * overallTaskList. It will also refresh any relevant component that may be
     * affected in its change.
     * @param taskList TaskList used for basis for the completedTasks
     * @param mode sort mode in integer form
     */
    private void refreshCompletedTasks(TaskList taskList, int mode){
        completedTasks.clear();
        for(Task task : taskList){
            if(task.isComplete())
                completedTasks.add(task);
        }
        switch(mode){
            case 1 -> completedTasks.sortByEndDate();
            case 2 -> completedTasks.sortByPriority();
            case 3 -> completedTasks.sortByName();
        }
        setupList();
        try{
            if(TASKawaii.getHomePage().getCalendarFrame() != null){
                TASKawaii.getHomePage().getCalendarFrame().refreshCalendar();
            }
        } catch(NullPointerException e){
            
        }
    }
    
    /**
     *This will set up the list of completed tasks.
     */
    private void setupList(){
        if(listPanel != null){
            listPanel.removeAll();
            listPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
            for(Task task : completedTasks){
                CompletedTaskListItem listItem = new CompletedTaskListItem(task);
                listItem.addMouseListener(this);
                if(task.equals(selectedTask)){
                    listItem.setBorder(BorderFactory.createLineBorder(UIManager.getColor("Button.default.focusedBorderColor"), 4));
                    listItem.setBackground(UIManager.getColor("Button.default.startBackground"));
                }
                listPanel.add(listItem);
            }
            listPanel.setPreferredSize(new Dimension(150, completedTasks.size()*75));
            listPanel.repaint();
            listPanel.revalidate();
        }
    }
    /**
     *This will refresh the CompletedTasksBar.
     * <p>
     * This is mainly used to refresh the CompletedTasksBar outside of the class itself and
     * preserves the sortMode currently selected.
     */
    public void refreshCompletedTasksBar(){
        refreshCompletedTasks(overallTaskList, sortMode);
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
     *This is the CompletedTasksBar's own copy of the overallTaskList that
     * stores only the complete tasks.
     */
    private TaskList completedTasks = new TaskList();

    /**
     *This is the reference of the CompletedTasksBar for the user TaskList.
     */
    private TaskList overallTaskList;

    /**
     *This is the current task selected by the user. Its value can be null.
     */
    private Task selectedTask = null;

    /**
     *This is the sort mode the CompletedTasksBar is currently in.
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
        CompletedTaskListItem item = (CompletedTaskListItem) e.getSource();
        if(selectedTask == null || !item.getTask().equals(selectedTask))
            selectedTask = item.getTask();
        else
            selectedTask = null;
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
        CompletedTaskListItem item = (CompletedTaskListItem) e.getSource();
        item.setBorder(BorderFactory.createLineBorder(UIManager.getColor("Button.default.focusedBorderColor"), 4, false));
        item.setBackground(UIManager.getColor("Button.default.startBackground"));
    }

    /**
     *This is the method if the mouse exits the listItem's area. It will clear the listItem's
     * border unless it is the selectedTask.
     * @param e MouseEvent that happened
     */
    @Override
    public void mouseExited(MouseEvent e) {
        CompletedTaskListItem item = (CompletedTaskListItem) e.getSource();
        if(!item.getTask().equals(selectedTask)){
            item.setBorder(null);
            item.setBackground(UIManager.getColor("Button.background"));
        }
    }
}
