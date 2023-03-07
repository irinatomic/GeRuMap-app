package dsw.gerumap.app.gui.swing.painters;

import dsw.gerumap.app.maprepository.implementation.Association;
import dsw.gerumap.app.maprepository.implementation.Element;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class AssociationPainter extends Painter{

    public AssociationPainter(Element association){
        super(association);
    }

    @Override
    public void draw(Graphics g) {
        Association a = (Association)super.getElement();
        a.recalculateCoordinates();
        Graphics2D g2D = (Graphics2D)g;
        Line2D.Double line = new Line2D.Double(a.getXFrom(), a.getYFrom(), a.getXTo(), a.getYTo());
        setShape(line);

        g2D.setStroke(new BasicStroke(a.getStrokeWidth()));
        g2D.setColor(new Color(a.getColourOutline()));

        g2D.draw(getShape());

        if(getSelected()){
            Rectangle2D rectangle = getShape().getBounds2D();
            g2D.setColor(Color.CYAN);
            g2D.draw(rectangle);
        }
    }

    @Override
    public boolean elementAt(int x, int y) {
        if(getShape() == null) return false;
        return getShape().getBounds2D().contains(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof AssociationPainter)) return false;
        AssociationPainter ap = (AssociationPainter) obj;
        return this.getElement().equals(ap.getElement());
    }
}
