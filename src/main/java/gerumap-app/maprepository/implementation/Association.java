package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.maprepository.composite.MapNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Association extends Element{

    private Topic from;
    private double xFrom, yFrom;
    private Topic to;
    private double xTo, yTo;


    public Association(String name, MapNode parent, Topic from) {
        super(name, parent);
        setFrom(from);
    }

    public void setFrom(Topic fromTopic){
        this.from = fromTopic;
        xFrom = from.getX() + (from.getWidth() / 2);
        yFrom = from.getY() + (from.getHeight() / 2);
        changedIt();
    }

    public void setTo(Topic toTopic){
        this.to = toTopic;
        xTo = to.getX() + (to.getWidth() / 2);
        yTo = to.getY() + (to.getHeight() / 2);
        changedIt();
    }

    //treba nam kada pomeramo topic da preracunamo nove pocetne ili krajnje koordinate
    public void recalculateCoordinates(){
        if(to == null) return;
        xFrom = from.getX() + (from.getWidth() / 2);
        yFrom = from.getY() + (from.getHeight() / 2);
        xTo = to.getX() + (to.getWidth() / 2);
        yTo = to.getY() + (to.getHeight() / 2);
        changedIt();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Association)) return false;
        Association a = (Association) obj;
        if(this.to == null)
            return this.from.equals(a.from);
        return this.from.equals(a.from) && this.to.equals(a.to) ||
                this.from.equals(a.to) && this.to.equals(a.from);
    }
}
