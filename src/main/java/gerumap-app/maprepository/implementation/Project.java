package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.observer.Notification;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Project extends MapNodeComposite {

    protected String filePath;
    protected boolean changed = true;
    private String author;

    public Project(String name, MapNode parent, String author) {
        super(name, parent);
        this.author = author;
    }

    @Override
    public void addChild(MapNode child){
        if(child != null && child instanceof MindMap) {
            MindMap newMindMap = (MindMap) child;
            if(!children.contains(newMindMap))
                children.add(child);
        }
        this.notifySubscribers(Notification.NEW);
        this.changed = true;
    }

    public void setAuthor(String author){
        this.author = author;
        this.notifySubscribers(Notification.ADD_AUTHOR);
        this.changed = true;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void setName(String name){
        super.setName(name);
        this.changed = true;
    }
}
