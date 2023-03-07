package dsw.gerumap.app.interfaces;

import dsw.gerumap.app.messages.IllegalEvent;
import dsw.gerumap.app.observer.IFPublisher;

public interface MessageGenerator extends IFPublisher {

    void createMessage(IllegalEvent illegalEvent);
}
