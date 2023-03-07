package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.state.StateManager;
import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.WorkSpaceImplementation;

import java.awt.event.ActionEvent;

//GRAFICKI DEO
public class DeleteAction extends AbstractRudokAction{

    public DeleteAction(){
        putValue(SMALL_ICON, loadIcon("/images/remove.png"));
        putValue(NAME, "Delete Action");
        putValue(SHORT_DESCRIPTION, "Delete Action");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getProjectView().getStateManager();
        stateManager.setDeleteState();
    }
}
