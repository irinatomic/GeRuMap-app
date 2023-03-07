package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.interfaces.ApplicationFramework;
import dsw.gerumap.app.maprepository.composite.*;
import dsw.gerumap.app.messages.IllegalEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class RenameAction extends AbstractRudokAction{

    public RenameAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/edit.png"));
        putValue(NAME, "Rename");
        putValue(SHORT_DESCRIPTION, "Rename");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeNode selectedWrapper = MainFrame.getInstance().getMapTree().getSelectedNode();
        if(selectedWrapper == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.NODE_NOT_SELECTED);
            return;
        }
        MapNode selected = selectedWrapper.getMapNode();

        String newName = JOptionPane.showInputDialog(new JFrame(), "Novo ime: ", "Izmeni ime komponente", JOptionPane.PLAIN_MESSAGE);
        if(newName.trim().equals("")){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.NAME_CANNOT_BE_EMPTY);
            return;
        } else if(((MapNodeComposite)selected.getParent()).cotainsSameNameComponent(newName)){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.EXISTS_SAME_NAME_COMPONENT);
            return;
        }
        selected.setName(newName);
    }
}