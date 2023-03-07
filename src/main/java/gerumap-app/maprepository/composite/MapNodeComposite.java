package dsw.gerumap.app.maprepository.composite;

import dsw.gerumap.app.observer.Notification;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
//oznacava da je nesto nasledivo
public abstract class MapNodeComposite extends MapNode{

    protected List<MapNode> children;

    public MapNodeComposite(String name, MapNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }
    public abstract void addChild(MapNode child);

    public void removeChild(MapNode child){
        if(child != null && !children.isEmpty())
            children.remove(child);
        child.notifySubscribers(Notification.REMOVE);
    }

    public MapNode getChildByName(String name) {
        for (MapNode child: children) {
            if (name.equals(child.getName())) {
                return child;
            }
        }
        return null;
    }

    public boolean cotainsSameNameComponent(String name){
        for(MapNode child : children) {
            if (child.getName().trim().equals(name.trim()))
                return true;
        }
        return false;
    }
}
