package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.composite.*;
import dsw.gerumap.app.observer.Notification;

public class ProjectExplorer extends MapNodeComposite {

    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public void addChild(MapNode child){
        if(child != null && child instanceof Project){
            Project newProject = (Project)child;
            if(!children.contains(newProject))
                children.add(child);
        }
        this.notifySubscribers(Notification.NEW);
    }
}
