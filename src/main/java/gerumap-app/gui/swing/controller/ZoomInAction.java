package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.WorkSpaceImplementation;
import dsw.gerumap.app.gui.swing.workspace.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ZoomInAction extends AbstractRudokAction{

    public ZoomInAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_PLUS));
        putValue(SMALL_ICON, loadIcon("/images/zoomIn.png"));
        putValue(NAME, "Zoom In");
        putValue(SHORT_DESCRIPTION, "Zoom In");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectView projectView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getProjectView();
        int selectedMapIndex = projectView.getTabbedPane().getSelectedIndex();
        MapView currMapView = projectView.getTabs().get(selectedMapIndex);
        currMapView.zoomIn();
    }
}
