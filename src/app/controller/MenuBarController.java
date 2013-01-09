package app.controller;

import app.MenuBar;
import app.common.MemberShip;
import app.common.listener.MenuBarListener.MenuBarListener;
import app.model.BoardModel;
import app.view.NavigationView;
import infrastructure.DatabaseContext;
import infrastructure.entity.Board;
import infrastructure.entity.Sticker;
import infrastructure.repository.BoardRepository;
import main.MemberShipModule;
import resources.TextResources;
import utils.patterns.mvc.AbstractController;
import utils.patterns.mvc.AbstractFrame;

import javax.swing.*;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 8.12.12
 * Time: 0:27
 */
public class MenuBarController extends AbstractController<BoardModel> implements MenuBarListener {

    private DatabaseContext databaseContext;

    public MenuBarController(AbstractFrame mainFrame, BoardModel model, MenuBar menuBar, DatabaseContext databaseContext) {
        super(mainFrame, model);
        this.databaseContext = databaseContext;
        menuBar.addMenuBarListener(this);
    }

    @Override
    public void createBoardAction() {
        String input = JOptionPane.showInputDialog(null, TextResources.Dialogs.CREATE_BOARD_MESSAGE,
                TextResources.Dialogs.CREATE_BOARD_TITLE, JOptionPane.QUESTION_MESSAGE);

        try {
            if (validateInput(input)) {
                Board newBoard = new Board(input);
                databaseContext.getBoardRepository().store(newBoard);
                databaseContext.getBoardRepository().commit();
                getModel().addBoard(newBoard);
                getModel().setSelectedBoard(newBoard);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, TextResources.Dialogs.INVALID_INPUT_MESSAGE,
                    TextResources.Dialogs.INVALID_INPUT_TITLE, JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void renameBoardAction() {
        Board selectedBoard = getModel().getSelectedBoard();
        String input = JOptionPane.showInputDialog(null, TextResources.Dialogs.RENAME_BOARD_MESSAGE, selectedBoard.getTitle());

        try {
            if (validateInput(input)) {
                selectedBoard.setTitle(input);
                databaseContext.getBoardRepository().store(selectedBoard);
                databaseContext.getBoardRepository().commit();
                getModel().update();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, TextResources.Dialogs.INVALID_INPUT_MESSAGE,
                    TextResources.Dialogs.INVALID_INPUT_TITLE, JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void addStickerAction() {
        Board selectedBoard = getModel().getSelectedBoard();
        String input = JOptionPane.showInputDialog(null, TextResources.Dialogs.CREATE_STICKER_MESSAGE,
                TextResources.Dialogs.CREATE_BOARD_TITLE, JOptionPane.QUESTION_MESSAGE);

        try {
            if (validateInput(input)) {
                selectedBoard.getStickers().add(new Sticker(input));
                databaseContext.getBoardRepository().store(selectedBoard);
                databaseContext.getBoardRepository().commit();
                getModel().update();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, TextResources.Dialogs.INVALID_INPUT_MESSAGE,
                    TextResources.Dialogs.INVALID_INPUT_TITLE, JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void saveStickersAction() {
        databaseContext.getBoardRepository().store(getModel().getSelectedBoard());
        databaseContext.getBoardRepository().commit();
        JOptionPane.showMessageDialog(null, TextResources.Dialogs.CONFIRM_SAVE_MESSAGE,
                TextResources.Dialogs.CONFIRM_SAVE_TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showNavigation() {
        getMainFrame().getView(NavigationView.class).show();
        getMainFrame().getController(NavigationController.class).update(null, null);
    }

    @Override
    public void removeBoard() {
        BoardRepository boardRepository = databaseContext.getBoardRepository();
        List<Board> boards = boardRepository.getAll();
        if (boards.size() <= 1) {
            JOptionPane.showMessageDialog(null, TextResources.Dialogs.REMOVE_BOARD_ERR1_MESSAGE,
                    TextResources.Dialogs.REMOVE_BOARD_ERR1_TITLE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        boardRepository.delete(getModel().getSelectedBoard());
        boardRepository.commit();

        getModel().setBoards(boardRepository.getAll());
        getModel().setSelectedBoard(boards.get(0));
    }

    @Override
    public void removeStickers() {
        Board selectedBoard = getModel().getSelectedBoard();
        String[] possibleStickers = new String[selectedBoard.getStickers().size()];
        List<Sticker> stickers = selectedBoard.getStickers();
        if (stickers.isEmpty()) {
            JOptionPane.showMessageDialog(null, TextResources.Dialogs.REMOVE_STICKER_ERR1_MESSAGE,
                    TextResources.Dialogs.REMOVE_STICKER_ERR1_TITLE, JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int i = 0; i < stickers.size(); i++) {
            Sticker sticker = stickers.get(i);
            possibleStickers[i] = sticker.getTitle();
        }

        String removeBoardTitle = (String) JOptionPane.showInputDialog(null,
                TextResources.Dialogs.REMOVE_STICKERS_MESSAGE,
                TextResources.Dialogs.REMOVE_STICKERS_TITLE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                possibleStickers,
                possibleStickers[0]);

        for (Sticker sticker : stickers) {
            if (sticker.getTitle().equals(removeBoardTitle)) {
                selectedBoard.getStickers().remove(sticker);
                databaseContext.getBoardRepository().store(selectedBoard);
                databaseContext.getBoardRepository().commit();
                getModel().setSelectedBoard(selectedBoard);
                return;
            }
        }
    }

    @Override
    public void changePassword() {
        new MemberShipModule(new MemberShip(databaseContext)).changePassword();

    }

    private boolean validateInput(String input) throws Exception {
        if (input == null)
            throw new Exception();

        if (input.equals(""))
            throw new Exception();

        return true;
    }
}
