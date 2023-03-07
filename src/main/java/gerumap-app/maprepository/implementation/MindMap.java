package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.maprepository.composite.*;
import dsw.gerumap.app.observer.Notification;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MindMap extends MapNodeComposite {

    public boolean template;

    public MindMap(String name, MapNode parent) {
        super(name, parent);
        this.template = false;
    }

    @Override
    public void addChild(MapNode child){
        if(child instanceof Element){
            Element newElement = (Element) child;
            if(!children.contains(newElement))
                children.add(child);
        }
        this.notifySubscribers(Notification.NEW);
        getParent().notifySubscribers(Notification.REPAINT);                //roditelj mape (Project) notifikuje ProjectView da uradi repaint()
        changedIt();
    }

    @Override
    public void setName(String name){
        super.setName(name);
        changedIt();
    }

    public void changedIt(){
        ((Project)getParent()).setChanged(true);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  MindMap)) return false;
        MindMap mindMap = (MindMap) obj;
        return mindMap.getName().equals(this.getName());
    }
}