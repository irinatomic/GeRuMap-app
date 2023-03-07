package dsw.gerumap.app.gui.swing.views;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getInfoAction()));
        fileMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getNewAction()));
        fileMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getSaveProjectAction()));
        fileMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getOpenProjectAction()));
        fileMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getSaveTemplateAction()));
        fileMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getOpenTemplateAction()));
        fileMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getExitAction()));

        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        editMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getAddAuthorAction()));
        editMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getRenameAction()));
        editMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getRemoveAction()));

        JMenu editSelectedMenu = new JMenu("Edit Selected");
        editSelectedMenu.setMnemonic(KeyEvent.VK_S);
        editSelectedMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getEditSelectedColorInsideAction()));
        editSelectedMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getEditSelectedColorOutsideAction()));
        editSelectedMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getEditSelectedNameAction()));

        this.add(fileMenu);
        this.add(editMenu);
        this.add(editSelectedMenu);
    }
}
