package dsw.gerumap.app.interfaces;

import dsw.gerumap.app.messages.Message;
import dsw.gerumap.app.observer.IFSubscriber;

public interface Logger extends IFSubscriber {
    void loggMessage(Message message);
}
