package ui.controls;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 17.12.12
 * Time: 13:15
 */
public class BoardScrollPanel extends JPanel {

    private BoardPanel boardPanel;

    public BoardScrollPanel(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
        this.boardPanel.setLayout(new WrapLayout());

        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(boardPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }
}
