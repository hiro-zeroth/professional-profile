package taskawaii.components;

import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import raven.toast.Notifications;
import taskawaii.control.Constants;
import taskawaii.control.TASKawaii;
import taskawaii.control.User;

/**
 *This class creates a ChangeNameDialogue to edit user name.
 * @author Raphael
 */
public class ChangeNameFrame extends JFrame {
    
    /**
     *The constructor takes in a User object and sets up the UI for this frame.
     * @param user
     */
    ChangeNameFrame(User user){
        this.user = user;
        setUI();
        initComponents();
    }
    
    /**
     *This initializes components for the ChangeNameFrame.
     */
    private void initComponents(){
        this.setTitle("TASKawaii");
        this.setSize(500, 250);
        
        TASKawaiiIcon = new ImageIcon("TASKawaiiLogo.png");
        label = new JLabel("TASKawaii", SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(500, 100));
        label.setIcon(new ImageIcon(TASKawaiiIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
        label.setHorizontalTextPosition(JLabel.LEFT);
        label.setForeground(Color.WHITE);
        
        Map<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.1);
        attributes.put(TextAttribute.SIZE, 40);
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        
        label.setFont(Constants.font.deriveFont(attributes));
        
        add(label);
        
        nameField = new HintTextField("Enter name here");
        nameField.setFont(Constants.font.deriveFont((float) 20));
        nameField.setPreferredSize(new Dimension(400, 40));
        nameField.setText(user.getName());
        
        getContentPane().setBackground(UIManager.getColor("Button.startBackground"));
        
        submit = new JButton("Submit");
        submit.setFont(Constants.font.deriveFont((float) 20));
        submit.setPreferredSize(new Dimension(100, 40));
        submit.addActionListener(e -> {
            if(!nameField.getText().isBlank()){
                user.setName(nameField.getText());
                String name = user.getName();
                if(name.charAt(name.length() - 1) == 's' || name.charAt(name.length() - 1) == 'S')
                    name += "'";
                else
                    name += "'s";
                TASKawaii.getHomePage().getUserLabel().setText(name + " Tasks");
                Notifications.getInstance().setJFrame(TASKawaii.getHomePage());
                Notifications.getInstance().show(Notifications.Type.SUCCESS, "User name changed.");
                dispose();
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, "No name input.");
            }
        });
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                Notifications.getInstance().setJFrame(TASKawaii.getHomePage());
                super.windowClosing(e);
            }
        });
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        add(nameField);
        add(submit);
        setResizable(false);
        setVisible(true);
        Notifications.getInstance().setJFrame(this);
    }
    
    /**
     *This sets up the UI for the JFrame.
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
     *This contains the TASKawaii label.
     */
    private JLabel label;

    /**
     *This is the ImageIcon for the TASKawaii logo.
     */
    private ImageIcon TASKawaiiIcon;

    /**
     *This is the HintTextField for the name input.
     */
    private HintTextField nameField; 

    /**
     *This is the JButton to submit the name input.
     */
    private JButton submit;

    /**
     *This is the user currently referenced in this JFrame.
     */
    private User user;
}
