package taskawaii.calendar;

import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import taskawaii.components.HomePage;
import taskawaii.control.Constants;
import taskawaii.control.TaskList;

/**
 *This is the main TaskCalendar frame.
 * @author Raphael
 */
public class TaskCalendar extends JFrame {

    /**
     *The constructor accepts a TaskList to generate a TaskCalendar with.
     * @param tasks TaskList to be used as reference for the TaskCalendar
     */
    public TaskCalendar(TaskList tasks){
        this.tasks = tasks;
        setUI();
        initComponents();
    }
    
    /**
     *This initializes the components for the JFrame.
     */
    private void initComponents(){
        taskCalendarBody = new TaskCalendarBody(tasks);
        
        labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout());
        labelPanel.setPreferredSize(new Dimension(200, 25));
        monthYearIndicator = new JLabel(taskCalendarBody.getTable().getDate().format(DateTimeFormatter.ofPattern("LLLL yyyy")), SwingConstants.CENTER);
        monthYearIndicator.setPreferredSize(new Dimension(200, 25));
        monthYearIndicator.setFont(Constants.font.deriveFont(Font.BOLD, (float) 14));
        labelPanel.add(monthYearIndicator);
        
        monthYearPanel = new JPanel();
        monthYearPanel.setLayout(new FlowLayout());
        monthYearPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        ImageIcon leftArrow = new ImageIcon("LeftArrow.png");
        decrementMonth = new JButton();
        decrementMonth.setIcon(new ImageIcon(leftArrow.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        decrementMonth.setPreferredSize(new Dimension(25, 25));
        decrementMonth.addActionListener(e -> {
            taskCalendarBody.getTable().decrementMonth();
            monthYearIndicator = new JLabel(taskCalendarBody.getTable().getDate().format(DateTimeFormatter.ofPattern("LLLL yyyy")), SwingConstants.CENTER);
            monthYearIndicator.setPreferredSize(new Dimension(200, 25));
            monthYearIndicator.setFont(Constants.font.deriveFont(Font.BOLD, (float) 14));
            labelPanel.removeAll();
            labelPanel.add(monthYearIndicator);
            labelPanel.repaint();
        });
        monthYearPanel.add(decrementMonth);
        
        monthYearPanel.add(labelPanel);
        
        ImageIcon rightArrow = new ImageIcon("RightArrow.png");
        incrementMonth = new JButton();
        incrementMonth.setIcon(new ImageIcon(rightArrow.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        incrementMonth.setPreferredSize(new Dimension(25, 25));
        incrementMonth.addActionListener(e -> {
            taskCalendarBody.getTable().incrementMonth();
            monthYearIndicator = new JLabel(taskCalendarBody.getTable().getDate().format(DateTimeFormatter.ofPattern("LLLL yyyy")), SwingConstants.CENTER);
            monthYearIndicator.setPreferredSize(new Dimension(200, 25));
            monthYearIndicator.setFont(Constants.font.deriveFont(Font.BOLD, (float) 14));
            labelPanel.removeAll();
            labelPanel.add(monthYearIndicator);
            labelPanel.repaint();
        });
        monthYearPanel.add(incrementMonth);
        
        setTitle("TASKawaii Calendar");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        add(monthYearPanel, BorderLayout.NORTH);
        add(taskCalendarBody, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }
    
    /**
     *This is the method for refreshing the calendar frame for changes in the user task list.
     */
    public void refreshCalendar(){
        taskCalendarBody.getTable().refreshTable(tasks);
    }
    
    /**
     *This sets up the UI for this frame.
     */
    private void setUI(){
        try{
            UIManager.setLookAndFeel(new FlatDarkPurpleIJTheme());
        }
        catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, "FlatLight not supported", ex);
        }
    }
    
    /**
     *This is the JPanel for the month and year label.
     */
    private JPanel labelPanel;

    /**
     *This is the JButton for decrementing the month.
     */
    private JButton decrementMonth;

    /**
     *This is the JButton for incrementing the month.
     */
    private JButton incrementMonth;

    /**
     *This is the JLabel that indicates the current month and year shown in the TaskCalendar.
     */
    private JLabel monthYearIndicator;

    /**
     *This is the JPanel for the Month and Year Indicator including its JButtons.
     */
    private JPanel monthYearPanel;

    /**
     *This is the where the calendar itself will be placed.
     */
    private TaskCalendarBody taskCalendarBody;

    /**
     *This is the TaskList the TaskCalendar will be referring to.
     */
    private TaskList tasks;
}
