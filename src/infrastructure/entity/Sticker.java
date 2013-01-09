package infrastructure.entity;

import java.io.Serializable;

/**
 * Author: Jirka Pénzeš
 * Date: 7.12.12
 * Time: 22:18
 */
public class Sticker extends Entity implements Serializable {

    private String title;
    private String text;

    public Sticker() {
        this("undefined");
    }

    public Sticker(String title) {
        this.title = title;
    }

    public String getText() {
        if (text == null) return "";
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        if (title == null) return "";
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
