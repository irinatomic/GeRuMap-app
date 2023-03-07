package dsw.gerumap.app.observer;

public interface IFPublisher {

    void addSubscriber(IFSubscriber subscriber);
    void removeSubscriber(IFSubscriber subscriber);
    void notifySubscribers(Object notification);
}
