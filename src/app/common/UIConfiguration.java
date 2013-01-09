package app.common;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 5.12.12
 * Time: 10:15
 */
public class UIConfiguration {

    private static UIConfiguration instance;
    private static Object lock = new Object();

    private UIConfiguration() {
    }

    public static UIConfiguration getInstance() {
        if (instance == null) {
            synchronized (lock) {
                instance = new UIConfiguration();
            }
        }
        return instance;
    }

    public Font getDefaultFont() {
        return new Font("Calibri", Font.PLAIN, 11);
    }

    // Event controls
    public Stroke getEventControlBorderStroke() {
        return new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    }

    public Color getEventControlBorderColor() {
        return new Color(79, 79, 79);
    }

    public Color getEventControlFillColor() {
        return new Color(158, 180, 228);
        //return new Color(255, 255, 164);
    }

}
