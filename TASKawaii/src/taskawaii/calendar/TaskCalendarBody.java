package taskawaii.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import taskawaii.control.Constants;
import taskawaii.control.TaskList;

/**
 *This is the JPanel where the calendar itself will be placed
 * @author Raphael
 */
public class TaskCalendarBody extends JPanel {

    /**
     *The constructor takes in a TaskList as a reference to generate the
     * dates and its corresponding tasks.
     * @param tasks
     */
    TaskCalendarBody(TaskList tasks){
        this.tasks = tasks;
        initComponents();
    }
    
    /**
     *This initializes the components for the JPanel.
     */
    private void initComponents(){
        dayOfWeeks = new JPanel();
        dayOfWeeks.setLayout(new GridLayout(1, 7));
        for(String day : days){
            JLabel dayLabel = new JLabel(day, SwingConstants.CENTER);
            if(day.equals("Sun") || day.equals("Sat"))
                dayLabel.setForeground(Color.red);
            dayLabel.setBorder(BorderFactory.createEtchedBorder());
            dayLabel.setPreferredSize(new Dimension(80, 50));
            dayLabel.setFont(Constants.font.deriveFont((float) 14));
            dayLabel.setBackground(UIManager.getColor("infoForeground"));
            dayLabel.setOpaque(true);
            dayOfWeeks.add(dayLabel);
        }
        
        table = new TaskCalendarTable(LocalDate.now().withDayOfMonth(1), tasks);
        
        setLayout(new BorderLayout());
        add(dayOfWeeks, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
    }
    
    /**
     *This is the getter for the TaskCalendarTable.
     * @return This will return the JPanel where the dates are placed.
     */
    public TaskCalendarTable getTable(){
        return table;
    }
    
    /**
     *This is the JPanel that will store the day of weeks JLabels.
     */
    private JPanel dayOfWeeks;

    /**
     *This is the array that stores the strings that will be printed for each day of the week.
     */
    private final String days[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    /**
     *This is the JPanel that will be used as a placeholder for the dates.
     */
    private TaskCalendarTable table;

    /**
     *This is the TaskList used as reference for the TaskCalendar.
     */
    private TaskList tasks;
}
