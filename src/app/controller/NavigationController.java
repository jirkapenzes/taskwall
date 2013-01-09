package app.controller;

import app.common.listener.MenuBarListener.NavigationListener;
import app.model.BoardModel;
import app.model.ConfigurationModel;
import app.view.NavigationView;
import infrastructure.DatabaseContext;
import infrastructure.entity.Board;
import utils.patterns.mvc.AbstractController;
import utils.patterns.mvc.AbstractFrame;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Author: Jirka Pénzeš
 * Date: 7.12.12
 * Time: 23:51
 */
public class NavigationController extends AbstractController<BoardModel> implements Observer, NavigationListener {

    private DatabaseContext databaseContext;

    public NavigationController(AbstractFrame mainFrame, BoardModel model, DatabaseContext databaseContext) {
        super(mainFrame, model);
        this.databaseContext = databaseContext;

        initialize();
        getModel().addObserver(this);
        getMainFrame().getView(NavigationView.class).addListener(this);
        Board board = ConfigurationModel.getInstance().getConfiguration().getSelectedBoard();
        if (board != null)
            getModel().setSelectedBoard(board);
    }

    private void initialize() {
        List<Board> boards = getModel().getBoards();
        getModel().setSelectedBoard(boards.get(0));
        getMainFrame().getView(NavigationView.class).updateModel(getModel());
    }

    @Override
    public void update(Observable o, Object arg) {
        getMainFrame().getView(NavigationView.class).updateModel(getModel());
    }

    @Override
    public void boardSelectedChanged(Board board) {
        getMainFrame().getController(BoardController.class).save();
        getModel().setSelectedBoard(board);
        ConfigurationModel.getInstance().getConfiguration().setSelectedBoard(board);
        ConfigurationModel.getInstance().saveChanges();
    }
}
