package taskawaii.control;

import java.io.Serializable;

/**
 *This is the class that initializes and sets up the priorities for tasks.
 * @author Raphael
 */
public class Priority implements Serializable {

    /**
     *This is the string-equivalent of the priority.
     */
    private final String priorityName;

    /**
     *This is the int-equivalent of the priority.
     */
    private final int priorityValue;

    /**
     *Low priority object
     */
    public static final Priority LOW = new Priority("Low", 1);

    /**
     *Medium priority object
     */
    public static final Priority MEDIUM = new Priority("Medium", 2);

    /**
     *High priority object
     */
    public static final Priority HIGH = new Priority("High", 3);

    /**
     *Immediate priority object
     */
    public static final Priority IMMEDIATE = new Priority("IMMEDIATE", 4);

    /**
     *The constructor is private since it prohibits any other classes to make
     * another type of priority. The only priorities available to other classes
     * are the ones declared in this class.
     * <p>
     * The constructor will take in a priorityName and a priorityValue.
     * @param priorityName the string-equivalent of the priority
     * @param priorityValue the int-equivalent of the priority
     */
    private Priority(String priorityName, int priorityValue){
        this.priorityName = priorityName;
        this.priorityValue = priorityValue;
    }

    /**
     *This converts a given string to its corresponding Priority object.
     * @param string the string to be converted to a priority object
     * @return It returns the corresponding priority object.
     */
    public static Priority toPriority(String string){
        switch(string){
            case "Low": return LOW;
            case "Medium": return MEDIUM;
            case "High": return HIGH;
            case "IMMEDIATE": return IMMEDIATE;
        }
        return null;
    }

    /**
     *This is the getter for the corresponding string of the priority object.
     * @return It will return the string-equivalent of the priority.
     */
    public String getPriorityName(){
        return priorityName;
    }

    /**
     *This is the getter for the corresponding int of the priority object.
     * @return It will return the int-equivalent of the priority.
     * <p>
     * (1 - Low, 2 - Medium, 3 - High, 4 - Immediate)
     */
    public int getPriorityValue(){
        return priorityValue;
    }
}
