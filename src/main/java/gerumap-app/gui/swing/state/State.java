package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.workspace.view.ProjectView;
import dsw.gerumap.app.maprepository.implementation.MindMap;

import java.awt.*;

public abstract class State {

    public abstract void mousePressed(Point e, ProjectView projectView, MindMap currMindMap);
    public abstract void mouseReleased(Point e, ProjectView projectView, MindMap currMindMap);
    public abstract void mouseDragged(Point e, ProjectView projectView, MindMap currMindMap);
}
