package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.interfaces.ApplicationFramework;
import dsw.gerumap.app.maprepository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class OpenProjectAction extends AbstractRudokAction{

    public OpenProjectAction(){
        putValue(NAME, "Open Project");
        putValue(SHORT_DESCRIPTION, "Open project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();

        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
            try {
                File file = jfc.getSelectedFile();
                Project p = ApplicationFramework.getInstance().getSerializer().loadProject(file);
                MainFrame.getInstance().getMapTree().loadProject(p);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
