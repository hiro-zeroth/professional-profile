package taskawaii.control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/**
 *This abstract class holds all the constant values of the program.
 * @author Raphael
 */
public abstract class Constants {
    
    /**
     * The constructor for the Constants class.
     */
    public Constants(){}
    
    /**
     *int value of the x-axis of the program at start
     */
    public static final int FX = 1920;

    /**
     *int value of the y-axis of the program at start
     */
    public static final int FY = 1080;

    /**
     *int value of the y-axis of the northPanel at start
     */
    public static final int NY = (int) (FY * 0.08);

    /**
     *int value of the y-axis of the southPanel at start
     */
    public static final int SY = (int) (FY * 0.03);

    /**
     *int value of the x-axis of the eastPanel at start
     */
    public static final int EX = (int) (FX * 0.10);

    /**
     *int value of the y-axis of the eastPanel at start
     */
    public static final int EY = FY - NY - SY;

    /**
     *int value of the x-axis of the westPanel at start
     */
    public static final int WX = (int) (FX * 0.15);

    /**
     *int value of the y-axis of the westPanel at start
     */
    public static final int WY = FY - NY - SY;

    /**
     *int value of the x-axis of the centerPanel at start
     */
    public static final int CX = (int) (FX * 0.75);

    /**
     *int value of the y-axis of the centerPanel at start
     */
    public static final int CY = (int) (FY * 0.78);

    /**
     *Dimension object of northPanel
     */
    public static final Dimension northSize = new Dimension(FX, NY);

    /**
     *Dimension object of southPanel
     */
    public static final Dimension southSize = new Dimension(FX, SY);

    /**
     *Dimension object of eastPanel
     */
    public static final Dimension eastSize = new Dimension(EX, EY);

    /**
     *Dimension object of westPanel
     */
    public static final Dimension westSize = new Dimension(WX, WY);

    /**
     *Default font for the whole program
     */
    public static Font font = new Font("Calibri", Font.PLAIN, 16);

    /**
     *Low priority color for pendingTasksBar
     */
    public static Color lowPriorityColor = new Color(58, 149, 17);

    /**
     *Medium priority color for pendingTasksBar
     */
    public static Color mediumPriorityColor = new Color(190, 97, 31);

    /**
     *High priority color for pendingTasksBar
     */
    public static Color highPriorityColor = new Color(107, 37, 66);

    /**
     *Immediate priority color for pendingTasksBar
     */
    public static Color immediatePriorityColor = new Color(136, 2, 182);

    /**
     *Error/due priority color for pendingTasksBar
     */
    public static Color errorColor = Color.RED;

    /**
     *Calendar date font color
     */
    public static Color taskDayFontColor = new Color(255, 255, 255);

    /**
     *Calendar cell color
     */
    public static Color taskDayColor = new Color(62, 20, 101);

    /**
     *Calendar date now font color
     */
    public static Color nowDayFontColor = new Color(150, 0, 250);
}