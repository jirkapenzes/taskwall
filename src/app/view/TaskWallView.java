package app.view;

import utils.patterns.mvc.AbstractFrame;
import utils.patterns.mvc.AbstractView;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 5.12.12
 * Time: 9:56
 */
public class TaskWallView extends AbstractView<JPanel> {

    public TaskWallView(AbstractFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    protected JPanel layout() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(getMainFrame().getView(NavigationView.class).getContentPane(), BorderLayout.WEST);
        contentPane.add(getMainFrame().getView(BoardView.class).getContentPane(), BorderLayout.CENTER);
        return contentPane;
    }
}
