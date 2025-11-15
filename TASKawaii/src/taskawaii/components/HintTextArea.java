package taskawaii.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JTextArea;

/**
 *This is a custom JTextArea that displays a hint for users.
 * @author Raphael
 */
public class HintTextArea extends JTextArea {

    /**
     *The constructor takes in a String that will serve as its hint.
     * @param hint String to be printed if empty
     */
    public HintTextArea(String hint) {
        textHint = hint;
    }

    /**
     *The paint method is overridden such that it performs the normal paint
     * operations but will add a hint if the text length is 0.
     * @param g Graphics object to be used
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getText().length() == 0) {
            int h = getHeight();
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            int c0 = getBackground().getRGB();
            int c1 = getForeground().getRGB();
            int m = 0xfefefefe;
            int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
            g.setColor(new Color(c2, true));
            g.setFont(g.getFont().deriveFont(Font.ITALIC));
            g.drawString(textHint, ins.left, fm.getAscent() + 10);
        }
    }

    /**
     *This will store the hint the HintTextArea will display.
     */
    private final String textHint;
}
