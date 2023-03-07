package dsw.gerumap.app.gui.swing.tree.controler;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.*;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.Project;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Getter
@Setter
public class MouseEventDemo implements MouseListener {

    private JTree myTree;

    public MouseEventDemo(JTree tree){
        this.myTree = tree;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int selRow = myTree.getRowForLocation(e.getX(), e.getY());              //pozicija selektovanog u tree

        if(selRow != -1 && e.getClickCount() == 2){
            MapTreeNode selectedWrapper = (MapTreeNode) myTree.getLastSelectedPathComponent();
            MapNode selectedNode = selectedWrapper.getMapNode();

            //ako je dvoklik na projekat -> stvaramo ProjectView (sa JTabbedPane) sa desne strane
            IFWorkspace workspace = MainFrame.getInstance().getWorkspace();
            if(workspace instanceof  WorkSpaceImplementation && selectedNode instanceof Project){
                ((WorkSpaceImplementation) workspace).getProjectView().updateWorkspace(selectedNode);
            }

            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
