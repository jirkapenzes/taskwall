package ui.controls;

import infrastructure.entity.Sticker;
import org.jdesktop.swingx.border.DropShadowBorder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Author: Jirka Pénzeš
 * Date: 7.12.12
 * Time: 22:13
 */
public class StickerControl extends JComponent {

    private JTextArea textarea;
    private JPanel panel;
    private Sticker sticker;

    public StickerControl(Sticker sticker) {
        this.sticker = sticker;
        setLocation(10, 10);
        setOpaque(false);
        setBackground(Color.lightGray);

        DropShadowBorder shadow = new DropShadowBorder();
        shadow.setShadowColor(Color.black);
        shadow.setShowLeftShadow(true);
        shadow.setShowRightShadow(true);
        shadow.setShowBottomShadow(true);
        shadow.setShowTopShadow(true);
        shadow.setShadowSize(8);
        setBorder(shadow);
        setLayout(new BorderLayout());

        textarea = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(textarea);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(40, 10, 10, 10));
        panel.setOpaque(false);
        textarea.setWrapStyleWord(true);
        textarea.setFont(new Font("Buxton Sketch", Font.PLAIN, 17));
        textarea.setLineWrap(true);
        panel.add(jScrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
        jScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        jScrollPane.getViewport().setBackground(new Color(255, 254, 188));
        textarea.setOpaque(false);
        textarea.setText(sticker.getText());
        setTitle(sticker.getTitle());
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(255, 254, 188), 0, getHeight(), new Color(255, 254, 188));
        graphics2D.setPaint(gradientPaint);
        graphics2D.fill(new Rectangle2D.Double(8, 8, getWidth() - 16, getHeight() - 16));

        graphics2D.setColor(new Color(224, 217, 166));
        graphics2D.fillRoundRect(15, 15, getWidth() - 30, 30, 10, 10);
        graphics2D.setColor(new Color(67, 67, 67));
        graphics2D.setFont(new Font("Buxton Sketch", Font.BOLD, 18));
        graphics2D.drawString(getTitle(), 20, 35);


    }

    public String getTitle() {
        return sticker.getTitle();
    }

    public void setTitle(String title) {
        sticker.setTitle(title);
        repaint();
    }

    public String getText() {
        return sticker.getText();
    }

    public void setText(String text) {
        sticker.setText(text);
        repaint();
    }

    public void commitTextToSticker() {
        setText(textarea.getText());
    }
}