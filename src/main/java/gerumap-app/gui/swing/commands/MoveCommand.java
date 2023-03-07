package dsw.gerumap.app.gui.swing.commands;

import dsw.gerumap.app.gui.swing.painters.Painter;
import dsw.gerumap.app.gui.swing.painters.TopicPainter;
import dsw.gerumap.app.gui.swing.workspace.view.*;
import dsw.gerumap.app.maprepository.implementation.*;

import java.util.ArrayList;
import java.util.List;

public class MoveCommand extends AbstractCommand{

    private MapView currMapView;
    private List<Painter> moved;
    private double translateX;
    private double translateY;
    private boolean firstTime = true;


    public MoveCommand(ProjectView projectView, double translateX, double translateY) {
        this.currMapView = (MapView) projectView.getTabbedPane().getSelectedComponent();
        this.moved = new ArrayList<>();
        for(Painter p : projectView.getSelectedComponents()){
            if(p instanceof TopicPainter)
                moved.add(p);
        }
        this.translateX = translateX;
        this.translateY = translateY;
    }

    @Override
    public void doCommand() {
        if(firstTime){
            firstTime = false;
            return;
        }

        for(Painter p : moved){
            Topic t = (Topic) p.getElement();
            double newXCoordinate = t.getX() + translateX;
            double newYCoordinate = t.getY() + translateY;
            checkCoordinates(t, newXCoordinate, newYCoordinate);
        }
    }

    @Override
    public void undoCommand() {
        for(Painter p : moved){
            Topic t = (Topic) p.getElement();
            double newXCoordinate = t.getX() - translateX;
            double newYCoordinate = t.getY() - translateY;
            checkCoordinates(t, newXCoordinate, newYCoordinate);
        }
    }

    private void checkCoordinates(Topic t, double xCoordinate, double yCoordinate){
        double workspaceHeight = currMapView.getSize().getHeight();
        double workspaceWidth = currMapView.getSize().getWidth();
        if(xCoordinate < 0) xCoordinate = 0;
        if(yCoordinate < 0) yCoordinate = 0;
        if(xCoordinate > workspaceWidth * 5 - t.getWidth()) xCoordinate = workspaceWidth * 5 - t.getWidth();
        if(yCoordinate > workspaceHeight * 5 - t.getHeight()) yCoordinate = workspaceHeight * 5 - t.getHeight();
        t.setX(xCoordinate);
        t.setY(yCoordinate);
    }
}