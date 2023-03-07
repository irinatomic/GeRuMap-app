package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.WorkSpaceImplementation;
import dsw.gerumap.app.gui.swing.workspace.view.ProjectView;
import lombok.Getter;

@Getter
public class StateManager {
    private NewTopicState newTopicState;
    private NewAssociationState newAssociationState;
    private DeleteState deleteState;
    private SelectState selectState;
    private MoveState moveState;
    private State currState;

    public StateManager(){
        newTopicState = new NewTopicState();
        newAssociationState = new NewAssociationState();
        deleteState = new DeleteState();
        selectState = new SelectState();
        moveState = new MoveState();
        currState = newTopicState;
    }

    public void setNewTopicState(){
        currState = newTopicState;
        removeSelection();
    }

    public void setNewAssociationState(){
        currState = newAssociationState;
        removeSelection();
    }

    public void setDeleteState(){
        currState = deleteState;
    }

    public void setSelectState(){
        currState = selectState;
    }

    public void setMoveState() {
        currState = moveState;
    }

    private void removeSelection(){
        ProjectView projectView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getProjectView();
        projectView.deselect();
    }
}