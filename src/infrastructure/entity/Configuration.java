package infrastructure.entity;

/**
 * Author: Jirka Pénzeš
 * Date: 20.12.12
 * Time: 10:56
 */
public class Configuration extends Entity {

    private Board selectedBoard;
    private boolean visibleNavigation;

    public Configuration() {
        visibleNavigation = true;
    }

    public Board getSelectedBoard() {
        return selectedBoard;
    }

    public void setSelectedBoard(Board selectedBoard) {
        this.selectedBoard = selectedBoard;
    }

    public boolean isVisibleNavigation() {
        return visibleNavigation;
    }

    public void setVisibleNavigation(boolean visibleNavigation) {
        this.visibleNavigation = visibleNavigation;
    }
}
