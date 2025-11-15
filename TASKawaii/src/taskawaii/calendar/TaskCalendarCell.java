package taskawaii.calendar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDate;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import taskawaii.control.Constants;
import taskawaii.control.Task;
import taskawaii.control.TaskList;

/**
 *This is the individual JPanel that a single date is placed to.
 * @author Raphael
 */
public class TaskCalendarCell extends JPanel {
    
    /**
     *The constructor takes in a date and a TaskList that corresponds to the date it contains.
     * @param date
     * @param tasks
     */
    TaskCalendarCell(LocalDate date, TaskList tasks){
        this.tasks = tasks;
        this.date = date;
        initComponents();
    }
    
    /**
     *This initializes the components for the JPanel.
     */
    private void initComponents(){
        taskPanel = new JPanel();
        taskPanel.setLayout(new FlowLayout());
        taskPanel.setPreferredSize(new Dimension(80, 80 + tasks.size()*30));
        taskPanel.setBorder(null);
        
        dateLabel = new JLabel("" + date.getDayOfMonth(), SwingConstants.CENTER);
        for(Task task : tasks){
            if(!task.isComplete()){
                dateLabel.setBackground(Constants.taskDayColor);
                dateLabel.setForeground(Constants.taskDayFontColor);
            }
        }
        dateLabel.setFont(Constants.font.deriveFont((float) 14));
        if(date.equals(LocalDate.now())){
            dateLabel.setForeground(Constants.nowDayFontColor);
            dateLabel.setFont(Constants.font.deriveFont(Font.BOLD | Font.ITALIC, 14));
        }
        dateLabel.setPreferredSize(new Dimension(80, 80));
        dateLabel.setOpaque(true);
        taskPanel.add(dateLabel);
        for(Task task : tasks){
            JLabel taskName = new JLabel(task.getTitle());
            taskName.setPreferredSize(new Dimension(80, 20));
            taskName.setBorder(new EmptyBorder(10, 10, 10, 10));
            taskName.setFont(Constants.font.deriveFont((float) 12));
            taskName.setOpaque(true);
            if(!task.isComplete())
                switch(task.getPriority().getPriorityValue()){
                    case 1 -> taskName.setBackground(Constants.lowPriorityColor);
                    case 2 -> taskName.setBackground(Constants.mediumPriorityColor);
                    case 3 -> taskName.setBackground(Constants.highPriorityColor);
                    case 4 -> taskName.setBackground(Constants.immediatePriorityColor);
                }
            if(task.isDue() && !task.isComplete())
                taskName.setBackground(Constants.errorColor);
            taskPanel.add(taskName);
        }
        
        cellContentScrollPane = new JScrollPane(taskPanel);
        cellContentScrollPane.setBorder(null);
        cellContentScrollPane.setPreferredSize(new Dimension(80, 80));
        cellContentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        cellContentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        cellContentScrollPane.getVerticalScrollBar().setUnitIncrement(5);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(80, 80));
        add(cellContentScrollPane, BorderLayout.CENTER);
    }
    
    /**
     *This is the getter for the date the cell contains.
     * @return It returns the corresponding date the cell was assigned to.
     */
    public LocalDate getDate(){
        return date;
    }
    
    /**
     *This is the date number JLabel.
     */
    private JLabel dateLabel;

    /**
     *This is where the date will be stored in the cell.
     */
    private LocalDate date;

    /**
     *This is the corresponding TaskList from the date assigned.
     */
    private TaskList tasks;

    /**
     *This is the JScrollPane that the contents of the cell will be placed into.
     */
    private JScrollPane cellContentScrollPane;

    /**
     *This is the JPanel that the corresponding tasks will be placed into.
     */
    private JPanel taskPanel;
}
