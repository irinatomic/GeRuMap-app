package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.observer.Notification;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//bice pojam ili veza
public abstract class Element extends MapNode {

    private int strokeWidth;
    private int colourInside;
    private int colourOutline;
    private boolean selected;

    public Element(String name, MapNode parent) {
        super(name, parent);
        strokeWidth = 2;            //2 pixels
        colourInside = 0xffffff;          //interior = white
        colourOutline = 0x000000;
    }

    public void setColourInside(String color){
        this.colourInside = Integer.decode(color);
        getParent().notifySubscribers(Notification.REPAINT);
        changedIt();
    }
    public void setColourOutline(String color) {
        this.colourOutline = Integer.decode(color);
        getParent().notifySubscribers(Notification.REPAINT);
        changedIt();
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        getParent().notifySubscribers(Notification.REPAINT);
    }

    @Override
    public void setName(String name){
        super.setName(name);
        changedIt();
    }

    public void changedIt(){
        ((MindMap)getParent()).changedIt();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Element)) return false;
        Element e = (Element) obj;
        return this.getName().equals(e.getName()) && this.getParent().equals(e.getParent());
    }
}
