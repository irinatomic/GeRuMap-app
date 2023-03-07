package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.interfaces.ApplicationFramework;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.messages.IllegalEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewAction extends AbstractRudokAction{

    public NewAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/newfile.png"));
        putValue(NAME, "New");
        putValue(SHORT_DESCRIPTION, "New");
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        //STVARA SE NOVI PROJEKAT/MAPA UMA/ELEMENT
        MapTreeNode selectedWrapper = MainFrame.getInstance().getMapTree().getSelectedNode();
        if(selectedWrapper == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.NODE_NOT_SELECTED);
            return;
        }
        MapNode selected = selectedWrapper.getMapNode();
        if(selected instanceof Element){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.CANNOT_ADD_CHILD);
            return;
        }
        MainFrame.getInstance().getMapTree().addChild(selectedWrapper, null);
    }
}
