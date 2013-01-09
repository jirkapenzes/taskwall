package utils.patterns.mvc;

import utils.ioc.IObjectContainer;
import utils.ioc.ObjectContainer;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 26.10.12
 * Time: 17:31
 */
public abstract class AbstractFrame {

    protected JFrame frame;
    protected final IObjectContainer<Class> views;
    protected final IObjectContainer<Class> controllers;
    protected IComponentFactory componentFactory;

    public AbstractFrame(IComponentFactory componentFactory) {
        this.componentFactory = componentFactory;
        this.views = new ObjectContainer<Class>();
        this.controllers = new ObjectContainer<Class>();

        initialize();
        registerAllViews();
        registerAllControllers();
    }

    protected abstract void initialize();

    protected abstract void registerAllViews();

    protected abstract void registerAllControllers();

    protected abstract JFrame layout();

    public void show() {
        if (frame == null) frame = layout();
        componentFactory.showFrame(frame);
    }

    public <View extends AbstractView<? extends JComponent>> View getView(Class<View> viewClass) {
        return views.get(viewClass);
    }

    public <Controller extends AbstractController> Controller getController(Class<Controller> controllerClass) {
        return controllers.get(controllerClass);
    }
}
