package taskawaii.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import taskawaii.control.Constants;

/**
 *This is the JPanel that holds the content HintTextArea and relevant actions.
 * @author Raphael
 */
public class TaskEditorPanelBody extends JPanel{
    
    /**
     *The constructor initializes the components.
     */
    TaskEditorPanelBody(){
        initComponents();
    }
    
    /**
     *This initializes the components of the TaskEditorPanelBody.
     */
    private void initComponents(){   
        textArea = new HintTextArea("Enter content here");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(true);
        textArea.setFocusable(true);
        textArea.setFont(Constants.font.deriveFont((float) 22));
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(UIManager.getColor("Button.borderColor")));
        
        fontSelector = new JComboBox(systemFonts);
        fontSelector.setFont(Constants.font.deriveFont((float) 14));
        fontSelector.addActionListener(e -> {
            textArea.setFont(new Font((String) fontSelector.getSelectedItem(), textArea.getFont().getStyle(), textArea.getFont().getSize()));
        });
        fontSelector.setPreferredSize(new Dimension(200, 30));
        fontSelector.setSelectedItem(Constants.font.getFamily());
        fontSelector.setEditable(true);
        fontSelector.setFocusable(false);
        
        fontSizeSelector = new JSpinner();
        fontSizeSelector.setFont(Constants.font.deriveFont((float) 12));
        fontSizeSelector.setPreferredSize(new Dimension(50, 30));
        fontSizeSelector.setValue(22);
        fontSizeSelector.addChangeListener(e -> {
            textArea.setFont(new Font(textArea.getFont().getFamily(), textArea.getFont().getStyle(), (int) fontSizeSelector.getValue()));
        });
        
        settingsPanel = new JPanel();
        settingsPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        settingsPanel.add(fontSelector);
        settingsPanel.add(fontSizeSelector);
        
        optionScrollPane = new JScrollPane(settingsPanel);
        optionScrollPane.setPreferredSize(new Dimension(Constants.CX, 50));
        optionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        optionScrollPane.getHorizontalScrollBar().setUnitIncrement(5);
        optionScrollPane.setBorder(null);
        
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);
        add(optionScrollPane, BorderLayout.NORTH);
        scrollPane.requestFocus();
    }
    
    /**
     *This is the getter for the textArea of the body.
     * @return This will return the HintTextArea of the TaskEditorPanelBody.
     */
    public HintTextArea getTextArea(){
        return textArea;
    }
    
    /**
     *This is the getter for the fontSelector of the body.
     * @return This will return the fontSelector of the TaskEditorPanelBody.
     */
    public JComboBox getFontSelector(){
        return fontSelector;
    }
    
    /**
     *This is the getter for the fontSizeSelector of the body.
     * @return This will return the fontSizeSelector of the TaskEditorPanelBody.
     */
    public JSpinner getFontSizeSelector(){
        return fontSizeSelector;
    }
    
    /**
     *This is the getter for the scrollPane of the textArea.
     * @return This will return the scrollPane of the textArea.
     */
    public JScrollPane getScrollPane(){
        return scrollPane;
    }
    
    /**
     *This is the array for the current system fonts the user has in their device.
     */
    public static String systemFonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    /**
     *This is the JPanel that will hold all the settings/actions the user can perform
     * on the task content.
     */
    private JPanel settingsPanel;

    /**
     *This is the HintTextArea that the user can input content on.
     */
    private HintTextArea textArea;

    /**
     *This is the JScrollPane that the textArea will be placed into.
     */
    private JScrollPane scrollPane;

    /**
     *This is the JScrollPane that the settingsPanel will be placed into.
     */
    private JScrollPane optionScrollPane;

    /**
     *This is the JComboBox for the fontSelector.
     */
    private JComboBox fontSelector;

    /**
     *This is the JSpinner for the fontSizeSelector.
     */
    private JSpinner fontSizeSelector;
}
