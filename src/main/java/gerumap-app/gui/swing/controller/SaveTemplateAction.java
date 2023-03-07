package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.WorkSpaceImplementation;
import dsw.gerumap.app.gui.swing.workspace.view.*;
import dsw.gerumap.app.interfaces.ApplicationFramework;
import dsw.gerumap.app.messages.IllegalEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveTemplateAction extends AbstractRudokAction{

    public SaveTemplateAction(){
        putValue(NAME, "Save Template");
        putValue(SHORT_DESCRIPTION, "Save Template");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectView projectView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getProjectView();
        MapView mapView = (MapView) projectView.getTabbedPane().getSelectedComponent();
        if(mapView == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.NODE_NOT_SELECTED);
            return;
        }

        String templateName = JOptionPane.showInputDialog(new JFrame(), "Unesite ime za template", "Template Name", JOptionPane.PLAIN_MESSAGE);
        if(templateName == null || templateName.trim().equals("")){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.NAME_CANNOT_BE_EMPTY);
            return;
        }

        ApplicationFramework.getInstance().getSerializer().saveTemplate(mapView.getMindMap(), templateName);
    }
}