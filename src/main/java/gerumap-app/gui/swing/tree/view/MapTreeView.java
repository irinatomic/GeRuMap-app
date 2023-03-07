package dsw.gerumap.app.gui.swing.tree.view;

import dsw.gerumap.app.gui.swing.tree.controler.MapTreeCellEditor;
import dsw.gerumap.app.observer.IFSubscriber;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

@Getter
@Setter
public class MapTreeView extends JTree implements IFSubscriber {

    private MapTreeCellEditor ourTreeEditor;
    public MapTreeView(DefaultTreeModel defaultTreeModel) {
        setModel(defaultTreeModel);

        MapTreeCellRenderer rudokTreeCellRenderer = new MapTreeCellRenderer();
        //addTreeSelectionListener(new MapTreeSelectionListener());

        ourTreeEditor = new MapTreeCellEditor(this, rudokTreeCellRenderer);
        setCellEditor(ourTreeEditor);
        setCellRenderer(rudokTreeCellRenderer);
        setEditable(true);
    }

    @Override
    public void update(Object notification) {
        SwingUtilities.updateComponentTreeUI(this);
    }
}
