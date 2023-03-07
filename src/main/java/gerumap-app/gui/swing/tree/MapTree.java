package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;

public interface MapTree {

    MapTreeView generateTree (ProjectExplorer projectExplorer);
    void addChild (MapTreeNode parent, MapNode child);
    void removeChild(MapTreeNode child);
    MapTreeNode getSelectedNode();
    void loadProject (Project node);
}
