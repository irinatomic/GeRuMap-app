package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.interfaces.ApplicationFramework;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import dsw.gerumap.app.messages.IllegalEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RemoveAction extends AbstractRudokAction{

    public RemoveAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/remove.png"));
        putValue(NAME, "Remove");
        putValue(SHORT_DESCRIPTION, "Remove");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeNode selectedWrapper = MainFrame.getInstance().getMapTree().getSelectedNode();
        if(selectedWrapper == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.NODE_NOT_SELECTED);
            return;
        }

        MapNode selected = selectedWrapper.getMapNode();
        if(selected instanceof ProjectExplorer){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.CANNOT_DELETE_PROJECTEXPLORER);
            return;
        }

        ((MapNodeComposite)selected.getParent()).removeChild(selected);
        MainFrame.getInstance().getMapTree().removeChild(MainFrame.getInstance().getMapTree().getSelectedNode());
    }
}
