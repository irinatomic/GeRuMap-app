package dsw.gerumap.app.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//KLASA KOJA JE SAMO IMPLEMENTIRAN IFPUBLISHER DA NE BI SVUDA IMPLEMENTIRALI SVE METODE

public class APublisher implements IFPublisher{

    private transient List<IFSubscriber> subscribers;

    @Override
    public void addSubscriber(IFSubscriber subscriber) {
        if(subscriber == null) return;
        if(subscribers == null) subscribers = new ArrayList<>();
        if(subscribers.contains(subscriber)) return;
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(IFSubscriber subscriber) {
        if(subscribers == null || subscribers.isEmpty() || !subscribers.contains(subscriber)) return;
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Object notification) {
        if(notification == null || subscribers == null || subscribers.isEmpty()) return;

        //Iterator jer kad removujemo subscribera iz liste -> ConcurrentModifiactionException
        Iterator<IFSubscriber> it = subscribers.iterator();
        while(it.hasNext()){
            IFSubscriber s = it.next();
            s.update(notification);
            if(notification.equals(Notification.REMOVE)) it.remove();
        }
    }
}
