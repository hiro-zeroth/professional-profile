package taskawaii.control;

import java.awt.Font;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *This is the most basic unit class of Tasks.
 * @author Raphael
 */
public class Task implements Serializable {

    /**
     *This is the variable to hold the title of the task.
     */
    private String title;

    /**
     *This is the variable to hold the content of the task.
     */
    private String content;

    /**
     *This is the variable to hold the priority of the task.
     */
    private Priority priority;

    /**
     *This is the variable to hold the deadline/endDateTime of the task.
     */
    private LocalDateTime endDateTime;

    /**
     *This is the variable to hold the state of the task.
     */
    private boolean completed;

    /**
     *This is the variable to hold the font of the task.
     */
    private Font font;
    
    /**
     *This is the constructor for the Task.
     * <p>
     * Will initialize Task font to default and set the task as incomplete.
     */
    public Task(){
        font = Constants.font.deriveFont((float) 22);
        completed = false;
    };

    /**
     *This is the setter for the title of the task.
     * @param title the task title to set
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     *This is the getter for the title of the task.
     * @return It returns the title of the task.
     */
    public String getTitle(){
        return title;
    }

    /**
     *This is the setter for the content of the task.
     * @param content the task content to set
     */
    public void setContent(String content){
        this.content = content;
    }

    /**
     *This is the getter for the content of the task.
     * @return It returns the content of the task.
     */
    public String getContent(){
        return content;
    }

    /**
     *This is the setter for the priority of the task.
     * @param priority the task priority to set
     */
    public void setPriority(Priority priority){
        this.priority = priority;
    }

    /**
     *This is the getter for the priority of the task.
     * @return It returns the priority of the task.
     */
    public Priority getPriority(){
        return priority;
    }

    /**
     *This is the setter for the endDateTime of the task.
     * @param endDateTime the task endDate/deadline to set
     */
    public void setEndDateTime(LocalDateTime endDateTime){
        this.endDateTime = endDateTime;
    }

    /**
     *This is the getter for the endDateTime of the task.
     * @return It returns the endDateTime/deadline of the task.
     */
    public LocalDateTime getEndDateTime(){
        return endDateTime;
    }

    /**
     *This will check if the task is already due based on LocalDateTime.now().
     * @return It will return true if the task is already due. It will return
     * false otherwise.
     */
    public boolean isDue(){
        if(endDateTime == null)
            return false;
        return endDateTime.isBefore(LocalDateTime.now());
    }

    /**
     *This will mark the task as complete.
     */
    public void markCompleted(){
        completed = true;
    }

    /**
     *This will mark the task as incomplete.
     */
    public void markNotCompleted(){
        completed = false;
    }

    /**
     *This checks if the task has been marked as complete.
     * @return It will return the boolean value of the completed variable.
     */
    public boolean isComplete(){
        return completed;
    }

    /**
     *This is the setter for the font of the task.
     * @param font the task font to set
     */
    public void setFont(Font font){
        this.font = font;
    }

    /**
     *This is the getter for the endDateTime of the task.
     * @return It returns the font of the task.
     */
    public Font getFont(){
        return font;
    }
}