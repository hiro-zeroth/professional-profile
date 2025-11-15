package taskawaii.calendar;

import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDate;
import javax.swing.JPanel;
import taskawaii.control.Task;
import taskawaii.control.TaskList;

/**
 *This is the overall JPanel for all the dates shown in the TaskCalendar.
 * @author Raphael
 */
public class TaskCalendarTable extends JPanel{

    /**
     *The constructor takes in the month and TaskList it will reference.
     * @param date
     * @param tasks
     */
    TaskCalendarTable(LocalDate date, TaskList tasks){
        refreshTaskList(tasks);
        this.date = date;
        initComponents();
    }
    
    /**
     *This initializes the components for the JPanel.
     */
    private void initComponents(){
        setLayout(new GridLayout(6, 7));
        dateIterator = date;
        if(dateIterator.getDayOfWeek().getValue() != 7){
            dateIterator = dateIterator.minusDays(dateIterator.getDayOfWeek().getValue());
        }
        for(int i = 0; i < 42; i++){
            LocalDate cellDate = dateIterator;
            TaskList temp = new TaskList();
            for(Task task : tasks){
                if(task.getEndDateTime().toLocalDate().equals(dateIterator))
                    temp.add(task);
            }
            TaskCalendarCell cell = new TaskCalendarCell(cellDate, temp);
            if(!(cell.getDate().getMonthValue() == date.getMonthValue()))
                cell.setBackground(Color.red);
            add(cell);
            dateIterator = dateIterator.plusDays(1);
        }
    }
    
    /**
     *This will refresh the reference TaskList of the table and sort it by endDate.
     * @param tasks
     */
    private void refreshTaskList(TaskList tasks){
        this.tasks = new TaskList();
        for(Task task : tasks)
            if(task.getTitle() != null)
                this.tasks.add(task);
        this.tasks.sortByEndDate();
    }
    
    /**
     *This is to refresh the overall table that holds each date.
     * @param tasks TaskList to be used as reference for the TaskCalendarTable
     */
    public void refreshTable(TaskList tasks){
        removeAll();
        refreshTaskList(tasks);
        initComponents();
        repaint();
        revalidate();
    }
    
    /**
     *This switches the table dates to the following month.
     * It will also refresh the Calendar Table.
     */
    public void incrementMonth(){
        date = date.plusMonths(1).withDayOfMonth(1);
        refreshTable(tasks);
    }
    
    /**
     *This switches the table dates to the preceding month.
     * It will also refresh the calendar table.
     */
    public void decrementMonth(){
        date = date.minusMonths(1).withDayOfMonth(1);
        refreshTable(tasks);
    }
    
    /**
     *This is the getter for the current month (date) the calendar is showing.
     * @return It returns the month (date) the calendar is currently showing.
     */
    public LocalDate getDate(){
        return date;
    }
    
    /**
     *This is the TaskList the table is currently referencing.
     */
    private TaskList tasks;

    /**
     *This is the date (month) the table is currently referencing.
     */
    private LocalDate date;

    /**
     *This is the iterator for the table to fill itself out.
     */
    private LocalDate dateIterator;
}
