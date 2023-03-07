package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.state.StateManager;
import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.WorkSpaceImplementation;

import java.awt.event.ActionEvent;

public class NewAssociationAction extends AbstractRudokAction{

    public NewAssociationAction(){
        putValue(SMALL_ICON, loadIcon("/images/newAssociationState.png"));
        putValue(NAME, "New Association");
        putValue(SHORT_DESCRIPTION, "New Association");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getProjectView().getStateManager();
        stateManager.setNewAssociationState();
    }
}
