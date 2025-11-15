package taskawaii.control;

import java.util.Vector;

/**
 *This is a class that extends from the built in Collection class, Vector.
 * Vectors were used in this project to accommodate synchronization since some
 * basic multi-threading was used in this program.
 * @author Raphael
 */
public class TaskList extends Vector<Task>{
    
    /**
    *This is the constructor for the TaskList class.
    */
    public TaskList(){}
    
    /**
     *This sorts the TaskList by its endDate by using a lambda expression
     * in the sort function to implement the Comparable interface.
     * <p>
     * If at least one endDate is null, o1 will be placed into a later position than o2.<p>
     * If o1 endDate is after o2 endDate, then o1 will be placed into a later position than o2.<p>
     * If o1 endDate is before o2 endDate, then o1 will be placed into an earlier position than o2.<p>
     * Else it will be considered equal and no switching of places will be made.
     */
    public void sortByEndDate(){
        sort((o1, o2) -> {
            if(o1.getEndDateTime() == null || o2.getEndDateTime() == null)
                return 1;
            if(o1.getEndDateTime().isAfter(o2.getEndDateTime()))
                return 1;
            else if(o1.getEndDateTime().isBefore(o2.getEndDateTime()))
                return -1;
            else
                return 0;
        });
    }

    /**
     *This sorts the TaskList by its priority by using a lambda expression
     * in the sort function to implement the Comparable interface.
     * <p>
     * If at least one priority is null, o1 will be placed into a later position than o2.<p>
     * Consider: o2.getPriority().getPriorityValue() - o1.getPriority().getPriorityValue()<p>
     * If value is positive, o1 will be placed in a later position than o2.<p>
     * If value is negative, o2 will be placed in a later position than o1.<p>
     * Else o1 priority will be considered equal to o2 priority. Hence, no switching of places will be made.
     */
    public void sortByPriority(){
        sort((o1, o2) -> {
            if(o1.getPriority() == null || o2.getPriority() == null)
                return 1;
            return o2.getPriority().getPriorityValue() - o1.getPriority().getPriorityValue();
        });
    }

    /**
     *This sorts the TaskList by its name by using a lambda expression
     * in the sort function to implement the Comparable interface.
     * <p>
     * If at least one title is null, o1 will be placed into a later position than o2.<p>
     * The compareToIgnoreCase method is used to compare o1 and o2 and will return the appropriate<p>
     * values to sort alphabetically in ascending order, regardless of case.
     */
    public void sortByName(){
        sort((o1, o2) -> {
            if(o1.getTitle() == null || o2.getTitle() == null)
                return 1;
            return o1.getTitle().compareToIgnoreCase(o2.getTitle());
        });
    }
}
