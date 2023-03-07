package dsw.gerumap.app.gui.swing.workspace.view;

import dsw.gerumap.app.gui.swing.commands.CommandManager;
import dsw.gerumap.app.gui.swing.painters.AssociationPainter;
import dsw.gerumap.app.gui.swing.painters.Painter;
import dsw.gerumap.app.gui.swing.painters.TopicPainter;
import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.*;
import dsw.gerumap.app.gui.swing.workspace.controller.MouseGraphicEvent;
import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Topic;
import dsw.gerumap.app.observer.*;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

@Getter
@Setter
public class MapView extends JPanel implements IFSubscriber {

    private MindMap mindMap;
    private List<Painter> painters = new ArrayList<>();
    private CommandManager commandManager;
    private AffineTransform affineTransform = new AffineTransform();
    private double scaling = 1.0;
    private double translateX = 0.0;
    private double translateY = 0.0;

    public MapView(MindMap mindMap){
        this.mindMap = mindMap;
        commandManager = new CommandManager();
        this.addMouseListener(new MouseGraphicEvent());
        this.addMouseMotionListener(new MouseGraphicEvent());           //za drag
    }

    @Override
    public void update(Object notification) {
        IFWorkspace workspace = MainFrame.getInstance().getWorkspace();
        ProjectView ourProjectView = ((WorkSpaceImplementation) workspace).getProjectView();
        int indexOfOurMap = ourProjectView.getTabbedPane().indexOfComponent(this);

        if(notification.equals(Notification.REMOVE)){
            ourProjectView.getTabs().remove(this);
            ourProjectView.getTabbedPane().remove(indexOfOurMap);
        }
        else if(notification.equals(Notification.RENAME)){
            ourProjectView.getTabbedPane().setTitleAt(indexOfOurMap, mindMap.getName());
        }

        ourProjectView.revalidate();
        ourProjectView.repaint();
    }

    public void zoomIn(){
        double newScaling = scaling * 1.2;
        if(newScaling >= 5) newScaling = 5;
        this.scaling = newScaling;
        setupTransformation(newScaling);
    }

    public void zoomOut(){
        double newScaling = scaling * 0.8;
        if(newScaling <= 0.2) newScaling = 0.2;
        this.scaling = newScaling;
        setupTransformation(newScaling);
    }

    public void scroll(double translateX, double translateY){
        this.translateX += translateX;
        this.translateY += translateY;
        setupTransformation(scaling);
    }

    private void setupTransformation(double scaling){
        affineTransform.setToIdentity();
        affineTransform.translate(translateX, translateY);
        affineTransform.scale(scaling, scaling);
        repaint();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapView mapView = (MapView) o;
        return mapView.mindMap.equals(this.mindMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mindMap);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g.create();
        g2D.transform(affineTransform);
        for(Painter p : painters){                                                                              //1. crtamo asocijacije da bi bile ispod pojmova
            if(p instanceof AssociationPainter) p.draw(g2D);
        }
        for(Painter p : painters){
            if(p instanceof TopicPainter) p.draw(g2D);
        }
        g2D.dispose();
    }

    public BufferedImage createImage() {
        // Nadji validne koordinate
        // min ti je koordinata pojma najblizeg 0,0
        int minY = 1000;
        int minX = 1000;
        int maxY = 0;
        int maxX = 0;
        for (Painter p: this.getPainters()){
            Element elem = p.getElement();
            if (elem instanceof Topic){
                System.out.println(((Topic) elem).getX() + " " + ((Topic) elem).getY());
                if ( maxX < ((Topic) elem).getX()) maxX = (int) ((Topic) elem).getX();
                if ( maxY < ((Topic) elem).getY()) maxY = (int) ((Topic) elem).getY();
                if ( minX > ((Topic) elem).getX()) minX = (int) ((Topic) elem).getX();
                if ( minY > ((Topic) elem).getY()) minY = (int) ((Topic) elem).getY();
            }
        }
        int desiredWidth = 620;
        int desiredHeight = 360;

        BufferedImage bufferedImage = new BufferedImage(desiredWidth, desiredHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        this.paint(g);
        return bufferedImage;
    }
}
