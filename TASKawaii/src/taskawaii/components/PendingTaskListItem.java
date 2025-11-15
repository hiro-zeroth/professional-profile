package taskawaii.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import taskawaii.control.Constants;
import taskawaii.control.Task;

/**
 *This is the JPanel that holds each task in the PendingTasksBar.
 * @author Raphael
 */
public class PendingTaskListItem extends JPanel {

    /**
     *This will hold the task it displays.
     */
    private Task task;

    /**
     *The constructor takes in the task that it will display.
     * @param task
     */
    PendingTaskListItem(Task task){
        Font font = Constants.font.deriveFont((float) 12);
        
        setPreferredSize(new Dimension(250, 70));
        this.task = task;
        setBackground(UIManager.getColor("Button.background"));
        
        String titleStr;
        if(task.getTitle() != null){
            titleStr = task.getTitle();
        } else {
            titleStr = "New Task";
        }
        JLabel title = new JLabel(titleStr);
        
        title.setFont(font.deriveFont(Font.BOLD, (float) 14));
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(200, 15));
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMMM d, yyyy (hh:mm a)");
        
        String endDateStr;
        if(task.getEndDateTime() != null){
            endDateStr = task.getEndDateTime().format(format);
        } else {
            endDateStr = "No Set Date and Time";
        }
        JLabel endDate = new JLabel(endDateStr);
        endDate.setFont(font);
        endDate.setForeground(Color.WHITE);
        endDate.setPreferredSize(new Dimension(200, 15));
        
        String priorityStr;
        if(task.getPriority() != null){
            priorityStr = "Priority: " + task.getPriority().getPriorityName();
            switch(task.getPriority().getPriorityValue()){
                case 1 -> setBackground(Constants.lowPriorityColor);
                case 2 -> setBackground(Constants.mediumPriorityColor);
                case 3 -> setBackground(Constants.highPriorityColor);
                case 4 -> setBackground(Constants.immediatePriorityColor);
            }
        } else {
            priorityStr = "No Set Priority";
        }
        JLabel priority = new JLabel(priorityStr);
        priority.setFont(font);
        priority.setForeground(Color.WHITE);
        priority.setPreferredSize(new Dimension(200, 15));
        
        
        add(title);
        add(endDate); 
        add(priority);
    }

    /**
     *This is the getter for the task the CompletedTaskListItem displays.
     * @return It will return the task contained by the listItem.
     */
    public Task getTask(){
        return task;
    }
}
