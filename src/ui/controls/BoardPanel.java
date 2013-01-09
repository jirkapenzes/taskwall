package ui.controls;

import infrastructure.entity.Sticker;
import resources.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 5.12.12
 * Time: 14:18
 */
public class BoardPanel extends JPanel {

    private Image texture;
    private List<StickerControl> stickers;

    public BoardPanel() {
        stickers = new ArrayList<StickerControl>();
        setLayout(new FlowLayout());
    }

    public void addSticker(final infrastructure.entity.Sticker sticker) {
        StickerControl stickerControl = new StickerControl(sticker);
        stickers.add(stickerControl);
        addStickerControl(stickerControl);
    }

    private void addStickerControl(final StickerControl stickerControl) {
        stickerControl.setPreferredSize(new Dimension(250, 250));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                stickerControl.revalidate();
            }
        });
        add(stickerControl);
        repaint();
        revalidate();
    }

    private Image getTexture() {
        if (texture == null)
            texture = Resources.getImage("board-texture.png");
        return texture;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(28, 28, 28));
        g.fillRect(0, 0, getWidth(), getHeight());

        Image texture = getTexture();
        Dimension offset = new Dimension(64, 64);

        for (int x = 0; x < getWidth(); x = x + offset.width) {
            for (int y = 0; y < getHeight(); y = y + offset.height)
                g.drawImage(texture, x, y, this);
        }
    }

    public void setStickers(List<Sticker> entityStickers) {
        stickers.clear();
        updateBoards();

        for (Sticker sticker : entityStickers) {
            addSticker(sticker);
        }
    }

    private void updateBoards() {
        removeAll();
        for (final StickerControl stickerControl : stickers) {
            addStickerControl(stickerControl);
        }
        invalidate();
        repaint();
    }

    public void updateStickerControls() {
        for (final StickerControl stickerControl : stickers) {
            stickerControl.commitTextToSticker();
        }
    }
}
