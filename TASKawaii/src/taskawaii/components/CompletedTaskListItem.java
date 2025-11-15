package taskawaii.components;

import java.awt.Dimension;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import taskawaii.control.Constants;
import taskawaii.control.Task;

/**
 *This is the JPanel that holds each task in the CompletedTasksBar.
 * @author Raphael
 */
public class CompletedTaskListItem extends JPanel {

    /**
     *This will hold the task it displays.
     */
    private Task task;

    /**
     *The constructor takes in the task that it will display.
     * @param task
     */
    CompletedTaskListItem(Task task){
        Font font = Constants.font.deriveFont((float) 12);
        
        setPreferredSize(new Dimension(150, 65));
        this.task = task;
        setBackground(UIManager.getColor("Button.background"));
        
        String titleStr;
        if(task.getTitle() != null){
            titleStr = task.getTitle();
        } else {
            titleStr = "New Task";
        }
        JLabel title = new JLabel(titleStr);
        title.setFont(font.deriveFont(Font.BOLD));
        title.setForeground(UIManager.getColor("Button.default.foreground"));
        title.setPreferredSize(new Dimension(100, 20));
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yy");
        
        String endDateStr;
        if(task.getEndDateTime() != null){
            endDateStr = task.getEndDateTime().format(format);
        } else {
            endDateStr = "No Set Date and Time";
        }
        JLabel endDate = new JLabel(endDateStr);
        endDate.setFont(font);
        endDate.setForeground(UIManager.getColor("Button.default.foreground"));
        endDate.setPreferredSize(new Dimension(100, 20));
        
        add(title);
        add(endDate);
    }
    
    /**
     *This is the getter for the task the CompletedTaskListItem displays.
     * @return It will return the task contained by the listItem.
     */
    public Task getTask(){
        return task;
    }
}
