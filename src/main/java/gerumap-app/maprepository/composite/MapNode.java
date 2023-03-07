package dsw.gerumap.app.maprepository.composite;

import dsw.gerumap.app.observer.APublisher;
import dsw.gerumap.app.observer.Notification;
import lombok.Getter;
import lombok.Setter;

//Klasa koja je ujedno i natklasa a kad se instancira je nenaslediva
//leaf u composite sablonu
@Getter
@Setter
public abstract class MapNode extends APublisher {
    private String name;
    private transient MapNode parent;

    public MapNode(String name, MapNode parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof MapNode)) return false;
        MapNode newObj = (MapNode) obj;
        return this.getName().equals(newObj.getName());
    }

    public void setName(String name){
        this.name = name;
        this.notifySubscribers(Notification.RENAME);
        this.notifySubscribers(Notification.REPAINT);
    }

    public void setParent(MapNode parent) {
        this.parent = parent;
        this.notifySubscribers(Notification.REPAINT);
    }
}
