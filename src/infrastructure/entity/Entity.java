package infrastructure.entity;

import java.io.Serializable;

/**
 * Author: Jirka Pénzeš
 * Date: 7.12.12
 * Time: 22:45
 */
public abstract class Entity implements Serializable {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
