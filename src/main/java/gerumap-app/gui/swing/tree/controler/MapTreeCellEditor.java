package dsw.gerumap.app.gui.swing.tree.controler;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.gerumap.app.interfaces.ApplicationFramework;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.messages.IllegalEvent;
import dsw.gerumap.app.observer.Notification;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class MapTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {


    public MapTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }

    private Object clickedOn = null;
    private JTextField edit = null;

    @Override
    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        //super.getTreeCellEditorComponent(tree, value, isSelected, expanded, leaf, row);
        clickedOn = arg1;
        edit = new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }

    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            return ((MouseEvent) arg0).getClickCount() == 3;
        return false;
    }

    public void actionPerformed(ActionEvent e){
        if (!(clickedOn instanceof MapTreeNode))
            return;

        String newName = e.getActionCommand();
        if(((MapNodeComposite)((MapTreeNode) clickedOn).getMapNode().getParent()).cotainsSameNameComponent(newName)) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.EXISTS_SAME_NAME_COMPONENT);
            return;
        }
        ((MapTreeNode) clickedOn).getMapNode().setName(e.getActionCommand());
    }
}
