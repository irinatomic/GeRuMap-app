package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.interfaces.ApplicationFramework;
import dsw.gerumap.app.maprepository.composite.*;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.messages.IllegalEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddAuthorAction extends AbstractRudokAction{

    public AddAuthorAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/addauthor.png"));
        putValue(NAME, "Add Author");
        putValue(SHORT_DESCRIPTION, "Add Author");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeNode selectedWrapper = MainFrame.getInstance().getMapTree().getSelectedNode();
        if(selectedWrapper == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.NODE_NOT_SELECTED);
            return;
        }

        MapNode selected = selectedWrapper.getMapNode();
        if(!(selected instanceof Project)){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.ONLY_PROJECT_CAN_HAVE_AUTHOR);
            return;
        }

        String newAuthor = JOptionPane.showInputDialog(new JFrame(), "Ime autora: ", "Dodaj ime autora", JOptionPane.PLAIN_MESSAGE);
        if(newAuthor.trim().equals("")){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.AUTHOR_CANNOT_BE_EMPTY);
            return;
        }
        ((Project)selected).setAuthor(newAuthor);
    }
}