package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.state.StateManager;
import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.WorkSpaceImplementation;

import java.awt.event.ActionEvent;

public class MoveAction extends AbstractRudokAction{

    public MoveAction(){
        putValue(SMALL_ICON, loadIcon("/images/moveState.png"));
        putValue(NAME, "Move Topic");
        putValue(SHORT_DESCRIPTION, "Move Topic");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getProjectView().getStateManager();
        stateManager.setMoveState();
    }
}
