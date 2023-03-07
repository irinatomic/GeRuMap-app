package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.state.StateManager;
import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.WorkSpaceImplementation;

import java.awt.event.ActionEvent;

public class SelectAction extends AbstractRudokAction{

    public SelectAction(){
        putValue(SMALL_ICON, loadIcon("/images/selectState.png"));
        putValue(NAME, "Select Topic");
        putValue(SHORT_DESCRIPTION, "Select");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getProjectView().getStateManager();
        stateManager.setSelectState();
    }
}
