package dsw.gerumap.app.gui.swing.tree.view;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class MapTreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        //u zavisnosti od izabranog objekta (value) setujemo ikonicu
        URL image = null;
        if (((MapTreeNode)value).getMapNode() instanceof ProjectExplorer) {
            image = getClass().getResource("/images/treeProjectExplorer.png");
        }
        else if (((MapTreeNode)value).getMapNode() instanceof Project) {
            image = getClass().getResource("/images/treeProject.png");
        }
        else if (((MapTreeNode)value).getMapNode() instanceof MindMap) {
            image = getClass().getResource("/images/treeMap.png");
        }
        else if (((MapTreeNode)value).getMapNode() instanceof Element) {
            image = getClass().getResource("/images/treeElement.png");
        }

        Icon icon = null;
        if (image != null)
            icon = new ImageIcon(image);
        setIcon(icon);

        return this;
    }
}
