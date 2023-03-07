package dsw.gerumap.app.interfaces;

import dsw.gerumap.app.observer.IFSubscriber;

public interface Gui extends IFSubscriber {
    void start();
    void disableUndoAction();
    void disableRedoAction();

    void enableUndoAction();
    void enableRedoAction();

}
