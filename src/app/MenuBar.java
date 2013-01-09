package app;

import app.common.listener.MenuBarListener.MenuBarListener;
import resources.TextResources;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 5.12.12
 * Time: 9:57
 */
public class MenuBar extends JMenuBar {

    private List<MenuBarListener> listeners;

    public MenuBar() {
        listeners = new ArrayList<MenuBarListener>();
        initializeMenuBar();
    }

    private void initializeMenuBar() {
        JMenu menu;
        JMenuItem menuItem;

        menu = new JMenu("File");
        menuItem = new JMenuItem("New model");
        menu.add(menuItem);
        add(menu);

        JMenu createBoard = new JMenu(TextResources.Menu.CREATE_BOARD);
        createBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                for (MenuBarListener listener : listeners)
                    listener.createBoardAction();
            }
        });

        JMenu renameBoard = new JMenu(TextResources.Menu.RENAME_BOARD);
        renameBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (MenuBarListener listener : listeners) {
                    listener.renameBoardAction();
                }
            }
        });

        JMenu addSticker = new JMenu(TextResources.Menu.ADD_STICKER);
        addSticker.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (MenuBarListener listener : listeners) {
                    listener.addStickerAction();
                }
            }
        });

        JMenu navigation = new JMenu(TextResources.Menu.SHOW_NAVIGATION);
        navigation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                for (MenuBarListener listener : listeners)
                    listener.showNavigation();
            }
        });

        JMenu saveAll = new JMenu(TextResources.Menu.SAVE_STICKERS);
        saveAll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (MenuBarListener listener : listeners) {
                    listener.saveStickersAction();
                }
            }
        });


        JMenu removeBoard = new JMenu(TextResources.Menu.REMOVE_BOARD);
        removeBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (MenuBarListener listener : listeners) {
                    listener.removeBoard();
                }
            }
        });

        JMenu removeStickers = new JMenu(TextResources.Menu.REMOVE_STICKERS);
        removeStickers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (MenuBarListener listener : listeners) {
                    listener.removeStickers();
                }
            }
        });

        JMenu changePassword = new JMenu(TextResources.Menu.CHANGE_PASSWORD);
        changePassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (MenuBarListener listener : listeners) {
                    listener.changePassword();
                }
            }
        });

        add(createBoard);
        add(renameBoard);
        add(addSticker);
        add(navigation);
        add(removeBoard);
        add(removeStickers);
        add(saveAll);
        add(changePassword);
    }

    public void addMenuBarListener(MenuBarListener menuBarListener) {
        listeners.add(menuBarListener);
    }
}
