package app.view;

import app.model.BoardModel;
import ui.controls.BoardScrollPanel;
import ui.controls.BoardPanel;
import utils.patterns.mvc.AbstractFrame;
import utils.patterns.mvc.AbstractView;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 5.12.12
 * Time: 14:19
 */
public class BoardView extends AbstractView {

    private Object changes;

    public BoardView(AbstractFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    protected JComponent layout() {
        return new BoardScrollPanel(new BoardPanel());
    }

    public void updateModel(BoardModel boardModel) {
        BoardPanel panel = ((BoardScrollPanel) getContentPane()).getBoardPanel();
        panel.setStickers(boardModel.getSelectedBoard().getStickers());
    }
}
