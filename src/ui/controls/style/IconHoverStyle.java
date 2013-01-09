package ui.controls.style;

import ui.controls.ImageButton;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 5.12.12
 * Time: 10:18
 */
public class IconHoverStyle implements IIconButtonHoverStyle {

    private Image image;

    public IconHoverStyle(Image image) {
        this.image = image;
    }

    @Override
    public void applyHoverStyle(ImageButton imageButton) {
        Graphics graphics = imageButton.getGraphics();
        graphics.clearRect(0, 0, imageButton.getWidth(), imageButton.getHeight());
        graphics.drawImage(image, 0, 0, imageButton.getWidth(), imageButton.getHeight(), imageButton);
    }
}