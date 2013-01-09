package infrastructure.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 7.12.12
 * Time: 22:17
 */
public class Board extends Entity implements Serializable {

    private String title;
    private List<Sticker> stickers;

    public Board(String title) {
        this.title = title;
        stickers = new ArrayList<Sticker>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Sticker> getStickers() {
        return stickers;
    }

    public void setStickers(List<Sticker> stickers) {
        this.stickers = stickers;
    }
}
