package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.observer.Notification;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Topic extends Element{

    private double x, y;
    private double width, height;

    public Topic(String name, MapNode parent, double x, double y) {
        super(name, parent);
        this.x = x;
        this.y = y;
    }

    public void setWidthAndHeight(double width, double height){
        this.width = width;
        this.height = height;
        changedIt();
    }

    public void setX(double x) {
        this.x = x;
        getParent().notifySubscribers(Notification.REPAINT);
        changedIt();
    }

    public void setY(double y) {
        this.y = y;
        getParent().notifySubscribers(Notification.REPAINT);
        changedIt();
    }

}
