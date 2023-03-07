package dsw.gerumap.app.logger;

import dsw.gerumap.app.interfaces.Logger;
import dsw.gerumap.app.messages.Message;

public class ConsoleLogger implements Logger {

    @Override
    public void loggMessage(Message message) {
        System.out.println(message);
    }

    @Override
    public void update(Object notification) {
        if(notification instanceof Message)
            loggMessage((Message) notification);
    }
}
