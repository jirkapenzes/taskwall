package infrastructure.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 5.12.12
 * Time: 11:48
 */
public class User extends Entity implements Serializable {

    private String userName;
    private String password;
    private List<Board> boards;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.boards = new ArrayList<Board>();
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }
}
