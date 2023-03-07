package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.painters.Painter;
import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.WorkSpaceImplementation;
import dsw.gerumap.app.gui.swing.workspace.view.ProjectView;
import dsw.gerumap.app.interfaces.ApplicationFramework;
import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.messages.IllegalEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class EditSelectedNameAction extends AbstractRudokAction{

    public EditSelectedNameAction(){
        putValue(SMALL_ICON, loadIcon("/images/edit.png"));
        putValue(NAME, "Change Name");
        putValue(SHORT_DESCRIPTION, "Change Name");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectView projectView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getProjectView();
        List<Painter> selectedPainters = projectView.getSelectedComponents();
        if(selectedPainters == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.NODE_NOT_SELECTED);
            return;
        }

        for (Painter p: selectedPainters) {
            Element selectedElement = p.getElement();
            String topicNewString = JOptionPane.showInputDialog(new JFrame(), "Promeni ime na", "Rename", JOptionPane.PLAIN_MESSAGE);
            if(topicNewString == null || topicNewString.trim().equals("")){
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.NAME_CANNOT_BE_EMPTY);
                return;
            }

            selectedElement.setName(topicNewString);
        }
    }
}
