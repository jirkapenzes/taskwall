package app.common;

import java.util.Observable;

/**
 * Author: Jirka Pénzeš
 * Date: 11.12.12
 * Time: 12:59
 */
public class AutomaticSaver extends Observable implements Runnable {

    private SaveObject saveObject;
    private long saveInterval;
    private boolean running;

    public AutomaticSaver(SaveObject saveObject, long saveInterval) {
        this.saveObject = saveObject;
        this.saveInterval = saveInterval;
    }


    @Override
    public void run() {
        running = true;
        while (isRunning()) {
            saveObject.save();
            notifyObservers("saved");
            try {
                Thread.sleep(saveInterval);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        running = false;
    }
}
