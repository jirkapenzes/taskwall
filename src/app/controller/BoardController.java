package app.controller;

import app.common.SaveObject;
import app.model.BoardModel;
import app.model.ConfigurationModel;
import app.view.BoardView;
import app.view.NavigationView;
import infrastructure.DatabaseContext;
import resources.TextResources;
import ui.controls.BoardPanel;
import ui.controls.BoardScrollPanel;
import utils.patterns.mvc.AbstractController;
import utils.patterns.mvc.AbstractFrame;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

/**
 * Author: Jirka Pénzeš
 * Date: 7.12.12
 * Time: 23:25
 */
public class BoardController extends AbstractController<BoardModel> implements Observer, SaveObject {

    private DatabaseContext databaseContext;

    public BoardController(AbstractFrame mainFrame, BoardModel model, DatabaseContext databaseContext) {
        super(mainFrame, model);
        this.databaseContext = databaseContext;

        initializeView();
        getModel().addObserver(this);
    }

    private void initializeView() {
        refresh();

    }


    @Override
    public void update(Observable o, Object arg) {
        refresh();
        ConfigurationModel.getInstance().getConfiguration().setSelectedBoard(getModel().getSelectedBoard());
        ConfigurationModel.getInstance().getConfiguration().setVisibleNavigation(getMainFrame().getView(NavigationView.class).isVisibleNavigation());
        ConfigurationModel.getInstance().getConfiguration().setSelectedBoard(getModel().getSelectedBoard());
    }

    private void refresh() {
        BoardView view = getMainFrame().getView(BoardView.class);
        view.updateModel(getModel());
    }

    @Override
    public void save() {
        BoardPanel boardPanel = ((BoardScrollPanel) getMainFrame().getView(BoardView.class).getContentPane()).getBoardPanel();
        boardPanel.updateStickerControls();

        databaseContext.getBoardRepository().store(getModel().getSelectedBoard());
        databaseContext.getBoardRepository().commit();

        Calendar currentDate = Calendar.getInstance();
        NavigationView navigationView = getMainFrame().getView(NavigationView.class);
        navigationView.setBottomText(
                TextResources.AUTO_SAVE + " " +
                        currentDate.get(Calendar.DAY_OF_MONTH) + "." +
                        currentDate.get(Calendar.MONTH) + "." +
                        currentDate.get(Calendar.YEAR) + " " +
                        currentDate.get(Calendar.HOUR) + ":" +
                        currentDate.get(Calendar.MINUTE) + ":" +
                        currentDate.get(Calendar.SECOND));
    }
}
