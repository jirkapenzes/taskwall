package app.model;

import infrastructure.entity.Board;
import utils.patterns.mvc.AbstractModel;

import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 7.12.12
 * Time: 23:25
 */
public class BoardModel extends AbstractModel {

    private Board selectedBoard;
    private List<Board> boards;

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
        setChanged();
        notifyObservers("boards");
    }

    public void addBoard(Board board) {
        this.boards.add(board);
        setChanged();
        notifyObservers("added_board");
    }

    public Board getSelectedBoard() {
        return selectedBoard;
    }

    public void setSelectedBoard(Board selectedBoard) {
        this.selectedBoard = selectedBoard;
        setChanged();
        notifyObservers("selectedBoard");
    }

    public void update() {
        setChanged();
        notifyObservers();
    }
}
