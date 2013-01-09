package ui.controls;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 5.12.12
 * Time: 10:10
 */
public class InternalPanel extends JComponent {

    private int panelThickness = 3;
    private boolean visibleIconBar = false;
    private JLabel bottomLabel;

    public InternalPanel(String titleName) {
        initializeComponents(titleName);
    }

    private void initializeComponents(String titleName) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0, panelThickness, 0, panelThickness));
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        // titlePanel.store(new GradientTitle(this, titleName, new Color(236, 236, 236), new Color(228, 228, 228)));
        titlePanel.add(new GradientTitle(this, titleName, new Color(236, 236, 236), new Color(228, 228, 228)));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(1, 5, 1, 3));
        bottomPanel.setBackground(new Color(130, 135, 144));
        bottomLabel = new JLabel("Authomatic saved on : 13:11:20");
        bottomLabel.setForeground(new Color(228, 228, 228));
        bottomPanel.add(bottomLabel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        if (visibleIconBar)
            titlePanel.add(new IconBar());

        add(titlePanel, BorderLayout.NORTH);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(192, 192, 192));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.white);
        g.fillRect(panelThickness, 0, getWidth() - (2 * panelThickness), getHeight());

        // g.setColor(new Color(192, 192, 192));
        // g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight());
    }

    public void setBottomText(String text) {
         bottomLabel.setText(text);
    }

}
