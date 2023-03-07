package dsw.gerumap.app.gui.swing.painters;

import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.maprepository.implementation.Topic;

import java.awt.*;
import java.awt.geom.*;

public class TopicPainter extends Painter{

    public TopicPainter(Element topic) {
        super(topic);
    }

    @Override
    public void draw(Graphics g) {
        Topic topic = (Topic)super.getElement();
        Graphics2D g2D = (Graphics2D)g;
        FontMetrics fm = g2D.getFontMetrics();
        int widthEllipse = fm.stringWidth(topic.getName()) + 20;
        int heightEllipse = 30;
        topic.setWidthAndHeight(widthEllipse, heightEllipse);
        //System.out.println("setujemo width and height " + topic.getX() + " " + topic.getY());
        setShape(new Ellipse2D.Double(topic.getX(), topic.getY(), widthEllipse, heightEllipse));

        g2D.setStroke(new BasicStroke(topic.getStrokeWidth()));                  //debljina liije
        g2D.setColor(new Color(topic.getColourInside()));                        //outline and text color
        g2D.fill(getShape());

        g2D.setColor(new Color(topic.getColourOutline()));
        g2D.draw(getShape());

        //ispisujemo string u pojam
        double xString = topic.getX() + ((widthEllipse - fm.stringWidth(topic.getName())) / 2);
        double yString = topic.getY() + (((heightEllipse - fm.getHeight()) / 2) + fm.getAscent());
        g2D.drawString(topic.getName(), (float)xString, (float) yString);

        if(getSelected()){
            Rectangle2D rectangle = getShape().getBounds2D();
            g2D.setColor(Color.CYAN);
            g2D.draw(rectangle);
        }
    }

    @Override
    public boolean elementAt(int x, int y) {
        return getShape().contains(x, y);
    }
}
