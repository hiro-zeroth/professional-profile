package taskawaii.components;

import taskawaii.calendar.TaskCalendar;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import raven.toast.Notifications;
import taskawaii.control.Constants;
import taskawaii.control.Task;
import taskawaii.control.TaskList;
import taskawaii.control.User;

/**
 *This is the main HomePage the user will see after they have successfully entered the
 * required user details.
 * @author Raphael
 */
public class HomePage extends JFrame implements Runnable{

    /**
     *This is the constructor for HomePage.
     * <p>
     * It sets the UI for the program and puts additional fonts for third-party classes in the UI.
     * It will also store the user it references into its own variable.
     * @param user user to be used in the HomePage as reference
     */
    public HomePage(User user){
        setUI();
        UIDefaults defaultUI = UIManager.getDefaults();
        defaultUI.put("Button.font", Constants.font);
        defaultUI.put("Label.font", Constants.font);
        defaultUI.put("ComboBox.font", Constants.font);
        defaultUI.put("TextArea.font", Constants.font);
        this.user = user;
        initComponents();
    }
    
    /**
     *This will initialize the components for the HomePage.
     */
    private void initComponents(){
        TASKawaiiIcon = new ImageIcon("TASKawaiiLogo.png");
        this.setIconImage(TASKawaiiIcon.getImage());
        setTitle("TASKawaii");
        setSize(Constants.FX, Constants.FY);
        menuBar = new JMenuBar();
        userMenu = new JMenu("User");
        changeName = new JMenuItem("Change Name");
        changeName.addActionListener(e -> {
            ChangeNameFrame changeNameFrame = new ChangeNameFrame(user);
        });
        userMenu.add(changeName);
        menuBar.add(userMenu);

        northPanel = new JPanel();
        northPanel.setPreferredSize(Constants.northSize);

        eastPanel = new CompletedTasksBar(user.getTaskList());
        eastPanel.setPreferredSize(Constants.eastSize);

        southPanel = new JPanel();
        southPanel.setPreferredSize(Constants.southSize);

        westPanel = new PendingTasksBar(user.getTaskList());
        westPanel.setPreferredSize(Constants.westSize);
        centerPanel = new TaskEditorPanel(null);
        
        String name = user.getName();
        if(name.charAt(name.length() - 1) == 's' || name.charAt(name.length() - 1) == 'S')
            name += "'";
        else
            name += "'s";
        userName = new JLabel(name + " Tasks");
        
        Map<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.1);
        attributes.put(TextAttribute.SIZE, 24);
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        
        userName.setFont(Constants.font.deriveFont(attributes));
        userName.setBorder(new EmptyBorder(10, 10, 10, 10));
        userName.setIcon(new ImageIcon(TASKawaiiIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        userName.setHorizontalTextPosition(JLabel.LEFT);
        userName.setForeground(Color.WHITE);
        
        calendarButton = new JButton("CALENDAR");
        calendarButton.setFont(Constants.font.deriveFont(Font.BOLD, (float) 18));
        calendarButton.setBackground(UIManager.getColor("Button.default.background"));
        calendarButton.setFocusable(false);
        calendarButton.setPreferredSize(new Dimension(130, 30));
        calendarButton.addActionListener(e -> {
            if(calendarFrame == null || calendarFrame.isShowing())
                calendarFrame = new TaskCalendar(user.getTaskList());
            else
                calendarFrame.requestFocus();
        });
        northPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        northPanel.setLayout(new BorderLayout());
        
        northPanel.add(userName, BorderLayout.EAST);
        northPanel.add(calendarButton, BorderLayout.WEST);
        northPanel.setBackground(UIManager.getColor("ComboBox.background"));
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                user.saveUser();
                super.windowClosing(e);
            }
        });
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setJMenuBar(menuBar);
        
        add(northPanel, BorderLayout.NORTH);
        add(eastPanel, BorderLayout.EAST);
        add(southPanel, BorderLayout.SOUTH);
        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        Notifications.getInstance().setJFrame(this);
    }
    
    /**
     *This will set up the UI for the HomePage.
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
     *This is the getter for the centerPanel.
     * @return This will return a non-specific JPanel in case of future updates
     * where the centerPanel can now be changed.
     */
    public JPanel getCenterPanel(){
        return centerPanel;
    }
    
    /**
     *This is the getter for the westPanel.
     * @return This will return a non-specific JPanel in case of future updates
     * where the westPanel can now be changed.
     */
    public JPanel getWestPanel(){
        return westPanel;
    }
    
    /**
     *This is the getter for the eastPanel.
     * @return This will return a non-specific JPanel in case of future updates
     * where the eastPanel can now be changed.
     */
    public JPanel getEastPanel(){
        return eastPanel;
    }
    
    /**
     *This is the getter for the TaskCalendar frame.
     * @return This will return the TaskCalendar frame. It may return null.
     */
    public TaskCalendar getCalendarFrame(){
        return calendarFrame;
    }
    
    /**
     *This is the getter for the user name JLabel.
     * @return This will return the JLabel used for displaying the user name.
     */
    public JLabel getUserLabel(){
        return userName;
    }
    
    /**
     *This is the getter for the user TaskList.
     * @return This will return the user TaskList.
     */
    public TaskList getTasks(){
        return user.getTaskList();
    }
    
    /**
     *This is the ImageIcon for the TASKawaii logo.
     */
    private ImageIcon TASKawaiiIcon;

    /**
     *This is where the user will be stored.
     */
    private final User user;

    /**
     *This is the JMenuBar of the HomePage.
     */
    private JMenuBar menuBar;

    /**
     *This is one of the JMenus available in the menuBar. It mainly deals with
     * actions that concern the user specifically.
     */
    private JMenu userMenu;

    /**
     *This is one of the JMenuItems for the userMenu. It will prompt the user for
     * the new name for its user profile.
     */
    private JMenuItem changeName;

    /**
     *This is the northPanel in the HomePage based on the BorderLayout.
     */
    private JPanel northPanel;

    /**
     *This is the eastPanel in the HomePage based from BorderLayout.
     */
    private JPanel eastPanel;

    /**
     *This is the southPanel in the HomePage based from BorderLayout.
     */
    private JPanel southPanel;

    /**
     *This is the westPanel in the HomePage based from BorderLayout.
     */
    private JPanel westPanel;

    /**
     *This is the centerPanel in the HomePage based from BorderLayout.
     */
    private JPanel centerPanel;

    /**
     *This is the JButton for opening the TaskCalendar frame.
     */
    private JButton calendarButton;

    /**
     *This is where the TaskCalendar frame will be stored if the calendarButton is pressed.
     */
    private TaskCalendar calendarFrame;

    /**
     *This is the JLabel for displaying the user name.
     */
    private JLabel userName;
    
    /**
     *This allows the HomePage to monitor due tasks every 15 seconds.
     * It uses multi-threading to allow simultaneous running.
     */
    @Override
    public void run() {
        while (true){
            Boolean reset = false;
            for(Task task : user.getTaskList()){
                if(task.isDue()){
                    reset = true;
                }
            }
            if(reset){
                ((PendingTasksBar) westPanel).refreshPendingTasksBar();
            }
            try {
                Thread.sleep(15000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}