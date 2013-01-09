package main;

import app.MainFrame;
import app.common.ComponentFactory;
import app.common.MemberShip;
import com.db4o.ObjectContainer;
import infrastructure.DatabaseContext;
import infrastructure.ObjectDatabase;
import infrastructure.repository.BoardRepository;
import infrastructure.repository.ConfigurationRepository;
import infrastructure.repository.UserRepository;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 5.12.12
 * Time: 10:00
 */
public class MainApplication {
    private static final Object lock = new Object();
    private static DatabaseContext databaseContext;

    public static DatabaseContext getDatabase() {
        synchronized (lock) {
            if (databaseContext == null) {
                ObjectContainer objectContainer = new ObjectDatabase().open();
                databaseContext = new DatabaseContext(
                        new BoardRepository(objectContainer),
                        new UserRepository(objectContainer),
                        new ConfigurationRepository(objectContainer));
            }
            return databaseContext;
        }
    }

    public static void main(String[] args) {
        MemberShip memberShip = new MemberShip(getDatabase());
        new MemberShipModule(memberShip).activate();

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new MainFrame(new ComponentFactory()).show();
                }
            });
        } catch (Exception exception) {
        }
    }

}
