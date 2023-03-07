package dsw.gerumap.app.gui.swing.workspace.controller;

import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.WorkSpaceImplementation;
import dsw.gerumap.app.gui.swing.workspace.view.MapView;
import dsw.gerumap.app.gui.swing.workspace.view.ProjectView;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import java.awt.geom.NoninvertibleTransformException;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class MouseGraphicEvent implements MouseListener, MouseMotionListener, MouseInputListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        //NE TREBA
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ProjectView projectView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getProjectView();
        MindMap currMindMap = ((MapView) projectView.getTabbedPane().getSelectedComponent()).getMindMap();
        Point worldP = getWorldCoordinates(e);
        projectView.getStateManager().getCurrState().mousePressed(worldP, projectView, currMindMap);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ProjectView projectView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getProjectView();
        MindMap currMindMap = ((MapView) projectView.getTabbedPane().getSelectedComponent()).getMindMap();
        Point worldP = getWorldCoordinates(e);
        projectView.getStateManager().getCurrState().mouseReleased(worldP, projectView, currMindMap);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //NE TREBA
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //NE TREBA
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ProjectView projectView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getProjectView();
        MindMap currMindMap = ((MapView) projectView.getTabbedPane().getSelectedComponent()).getMindMap();
        Point worldP = getWorldCoordinates(e);
        projectView.getStateManager().getCurrState().mouseDragged(worldP, projectView, currMindMap);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //NE TREBA
    }

    private Point getWorldCoordinates(MouseEvent me){
        ProjectView projectView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getProjectView();
        MapView currMapView = (MapView) projectView.getTabbedPane().getSelectedComponent();

        AffineTransform atInvert = null;
        try {
            atInvert = currMapView.getAffineTransform().createInverse();
        } catch(NoninvertibleTransformException | NullPointerException exception){
            System.err.print("Non invertible transformation");
        }

        Point2D pDest = atInvert.transform(new Point2D.Double(me.getX(), me.getY()), null);
        Point pDest2 = new Point();
        pDest2.x = (int) pDest.getX();
        pDest2.y = (int) pDest.getY();
        return pDest2;
    }
}
