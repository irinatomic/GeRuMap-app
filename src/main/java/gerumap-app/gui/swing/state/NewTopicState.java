package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.commands.NewTopicCommand;
import dsw.gerumap.app.gui.swing.workspace.view.MapView;
import dsw.gerumap.app.gui.swing.workspace.view.ProjectView;
import dsw.gerumap.app.maprepository.implementation.*;

import java.awt.*;

public class NewTopicState extends State{

    @Override
    public void mousePressed(Point e, ProjectView projectView, MindMap currMindMap) {
        NewTopicCommand ntc = new NewTopicCommand(e, projectView, currMindMap);
        MapView currMapView = (MapView) projectView.getTabbedPane().getSelectedComponent();
        currMapView.getCommandManager().addCommand(ntc);
    }

    @Override
    public void mouseReleased(Point e, ProjectView projectView, MindMap currMindMap) {
        //NISTA
    }

    @Override
    public void mouseDragged(Point e, ProjectView projectView, MindMap currMindMap) {
        //NISTA
    }
}
