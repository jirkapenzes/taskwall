package app.view;

import app.common.listener.MenuBarListener.NavigationListener;
import app.model.BoardModel;
import app.model.ConfigurationModel;
import ui.controls.Navigation;
import utils.patterns.mvc.AbstractFrame;
import utils.patterns.mvc.AbstractView;

/**
 * Author: Jirka Pénzeš
 * Date: 5.12.12
 * Time: 10:09
 */
public class NavigationView extends AbstractView<Navigation> {

    private String bottomText;

    public NavigationView(AbstractFrame mainFrame) {
        super(mainFrame);
        boolean showIt = ConfigurationModel.getInstance().getConfiguration().isVisibleNavigation();
        if (showIt)
            show();
        else
            hide();
    }

    @Override
    protected Navigation layout() {
        return new Navigation("Project navigation");
    }

    public void updateModel(BoardModel boardModel) {
        getContentPane().addBoards(boardModel.getBoards());
        getContentPane().setSelectedBoard(boardModel.getSelectedBoard());
    }

    public void addListener(NavigationListener listener) {
        getContentPane().addActionListener(listener);
    }

    public void setBottomText(String bottomText) {
        getContentPane().setBottomText(bottomText);
    }

    public void show() {
        getContentPane().setVisible(true);
        ConfigurationModel.getInstance().getConfiguration().setVisibleNavigation(isVisibleNavigation());
        ConfigurationModel.getInstance().saveChanges();
    }

    public void hide() {
        getContentPane().setVisible(false);
        ConfigurationModel.getInstance().getConfiguration().setVisibleNavigation(isVisibleNavigation());
        ConfigurationModel.getInstance().saveChanges();
    }

    public boolean isVisibleNavigation() {
        return getContentPane().isVisible();
    }
}
