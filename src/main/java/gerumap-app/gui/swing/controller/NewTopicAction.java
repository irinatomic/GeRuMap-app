package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.state.StateManager;
import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.WorkSpaceImplementation;

import java.awt.event.ActionEvent;

public class NewTopicAction extends AbstractRudokAction{

    public NewTopicAction(){
        putValue(SMALL_ICON, loadIcon("/images/newTopicState.png"));
        putValue(NAME, "New Topic");
        putValue(SHORT_DESCRIPTION, "New Topic");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = ((WorkSpaceImplementation)MainFrame.getInstance().getWorkspace()).getProjectView().getStateManager();
        stateManager.setNewTopicState();
    }
}
