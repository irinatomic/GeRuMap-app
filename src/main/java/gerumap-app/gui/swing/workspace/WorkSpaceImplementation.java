package dsw.gerumap.app.gui.swing.workspace;

import dsw.gerumap.app.gui.swing.workspace.view.ProjectView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkSpaceImplementation implements IFWorkspace{

    private ProjectView projectView;

    @Override
    public ProjectView generateWorkspace() {
        projectView = new ProjectView();
        return projectView;
    }
}
