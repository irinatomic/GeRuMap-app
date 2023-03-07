package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.commands.MoveCommand;
import dsw.gerumap.app.gui.swing.painters.Painter;
import dsw.gerumap.app.gui.swing.painters.TopicPainter;
import dsw.gerumap.app.gui.swing.workspace.view.*;
import dsw.gerumap.app.maprepository.implementation.*;

import java.awt.*;

public class MoveState extends State{

    private Boolean movingComponents = false;
    private Point startPosition = null;
    private Point prevDragPosition = null;

    @Override
    public void mousePressed(Point e, ProjectView projectView, MindMap currMindMap) {
        //Nista nije selektovano -> pomeramo celu mapu
        //Selektovano je vec nesto -> pomeramo selektovano
        startPosition = e;
        prevDragPosition = startPosition;
        if(!projectView.getSelectedComponents().isEmpty())
            movingComponents = true;
    }

    @Override
    public void mouseReleased(Point e, ProjectView projectView, MindMap currMindMap) {
        MoveCommand mc = new MoveCommand(projectView, e.getX() - startPosition.getX(), e.getY() - startPosition.getY());
        MapView currMapView = (MapView) projectView.getTabbedPane().getSelectedComponent();
        currMapView.getCommandManager().addCommand(mc);

        movingComponents = false;
        startPosition = null;
        prevDragPosition = null;
    }

    @Override
    public void mouseDragged(Point e, ProjectView projectView, MindMap currMindMap) {
        MapView currMapView = (MapView) projectView.getTabbedPane().getSelectedComponent();

        //Pomeramo mapu (krecemo se po njoj)
        if(!movingComponents){
            currMapView.scroll(e.getX() - startPosition.getX(), e.getY() - startPosition.getY());
            return;
        }

        //Pomeramo selektovane elemente
        for(Painter p : projectView.getSelectedComponents()){
            if(p instanceof TopicPainter){
                Topic topicBeingMoved = (Topic) p.getElement();
                double newXCoordinate = topicBeingMoved.getX() + e.getX() - prevDragPosition.getX();
                double newYCoordinate = topicBeingMoved.getY() + e.getY() - prevDragPosition.getY();

                //pojam ne sme da izadje izvan okvira mape
                double workspaceHeight = currMapView.getSize().getHeight();
                double workspaceWidth = currMapView.getSize().getWidth();
                if(newXCoordinate < 0) newXCoordinate = 0;
                if(newYCoordinate < 0) newYCoordinate = 0;
                if(newXCoordinate > workspaceWidth * 5 - topicBeingMoved.getWidth()) newXCoordinate = workspaceWidth * 5 - topicBeingMoved.getWidth();
                if(newYCoordinate > workspaceHeight * 5 - topicBeingMoved.getWidth()) newYCoordinate = workspaceHeight * 5 - topicBeingMoved.getWidth();
                topicBeingMoved.setX(newXCoordinate);
                topicBeingMoved.setY(newYCoordinate);
            }
        }
        prevDragPosition = e;
    }
}