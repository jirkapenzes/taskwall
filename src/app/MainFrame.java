package app;

import app.common.AutomaticSaver;
import app.controller.BoardController;
import app.controller.MenuBarController;
import app.controller.NavigationController;
import app.model.BoardModel;
import app.view.BoardView;
import app.view.NavigationView;
import app.view.TaskWallView;
import infrastructure.DatabaseContext;
import infrastructure.entity.Board;
import main.MainApplication;
import resources.TextResources;
import utils.patterns.mvc.AbstractFrame;
import utils.patterns.mvc.IComponentFactory;

import javax.swing.*;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 5.12.12
 * Time: 9:53
 */
public class MainFrame extends AbstractFrame {

    private DatabaseContext databaseContext;
    private MenuBar menuBar;
    private BoardModel boardModel;

    public MainFrame(IComponentFactory componentFactory) {
        super(componentFactory);
        new Thread(new AutomaticSaver(getController(BoardController.class), 2000)).start();
    }

    @Override
    protected void initialize() {
        databaseContext = MainApplication.getDatabase();
        menuBar = new MenuBar();

        List<Board> boards = databaseContext.getBoardRepository().getAll();
        if (boards.isEmpty()) {
            databaseContext.getBoardRepository().store(new Board(TextResources.INIT_BOARD));
            boards = databaseContext.getBoardRepository().getAll();
        }
        boardModel = new BoardModel();
        boardModel.setBoards(boards);
        boardModel.setSelectedBoard(boards.get(0));
    }

    @Override
    protected void registerAllViews() {
        views.bind(TaskWallView.class, new TaskWallView(this));
        views.bind(NavigationView.class, new NavigationView(this));
        views.bind(BoardView.class, new BoardView(this));
    }

    @Override
    protected void registerAllControllers() {
        controllers.bind(MenuBarController.class, new MenuBarController(this, boardModel, menuBar, databaseContext));
        controllers.bind(NavigationController.class, new NavigationController(this, boardModel, databaseContext));
        controllers.bind(BoardController.class, new BoardController(this, boardModel, databaseContext));
    }

    @Override
    protected JFrame layout() {
        return componentFactory.frame(TextResources.APPLICATION_NAME, getView(TaskWallView.class).getContentPane(), menuBar);
    }
}
