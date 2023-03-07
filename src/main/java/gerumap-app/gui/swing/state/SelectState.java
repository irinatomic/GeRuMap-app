package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.painters.LassoPainter;
import dsw.gerumap.app.gui.swing.painters.Painter;
import dsw.gerumap.app.gui.swing.workspace.view.ProjectView;
import dsw.gerumap.app.maprepository.implementation.MindMap;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class SelectState extends State{

    private Point mousePress = null;
    private LassoPainter lassoPainter = null;

    @Override
    public void mousePressed(Point e, ProjectView projectView, MindMap currMindMap) {
        mousePress = e;
        projectView.deselect();
        lassoPainter = new LassoPainter(e.getX(), e.getY());
        projectView.getPaintersForMap().get(currMindMap).add(lassoPainter);
    }

    @Override
    public void mouseDragged(Point e, ProjectView projectView, MindMap currMindMap) {
        lassoPainter.updateCoordinates(mousePress.getX(), mousePress.getY(), e.getX(), e.getY());
        projectView.repaint();
    }

    @Override
    public void mouseReleased(Point e, ProjectView projectView, MindMap currMindMap) {
        projectView.getPaintersForMap().get(currMindMap).remove(lassoPainter);

        List<Painter> currMindMapPainters = projectView.getPaintersForMap().get(currMindMap);
        for(Painter p : currMindMapPainters){
            if(p.getShape().intersects((Rectangle2D) lassoPainter.getShape())) {
                projectView.getSelectedComponents().add(p);
                p.setSelected(true);
            } else
                p.setSelected(false);
        }

        projectView.deletePainterForCurrent(lassoPainter);
        lassoPainter = null;
    }
}