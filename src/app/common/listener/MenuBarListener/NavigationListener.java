package app.common.listener.MenuBarListener;

import infrastructure.entity.Board;

/**
 * Author: Jirka Pénzeš
 * Date: 11.12.12
 * Time: 0:09
 */
public interface NavigationListener {

    public void boardSelectedChanged(Board board);
}
