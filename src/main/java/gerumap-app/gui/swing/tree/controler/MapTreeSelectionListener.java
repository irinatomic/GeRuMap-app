package dsw.gerumap.app.gui.swing.tree.controler;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeNode;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.awt.event.MouseListener;

public class MapTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        MapTreeNode treeNodeSelected = (MapTreeNode)path.getLastPathComponent();

    }
}
