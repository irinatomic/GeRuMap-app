package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.interfaces.ApplicationFramework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class OpenTemplateAction extends AbstractRudokAction{

    public OpenTemplateAction(){
        putValue(NAME, "Open Template");
        putValue(SHORT_DESCRIPTION, "Open Template");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        String workingDirectory = System.getProperty("user.dir");
        String templatesPath = workingDirectory + "/src/main/resources/templates";
        jfc.setCurrentDirectory(new File(templatesPath));

        if(jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
            try{
                File file = jfc.getSelectedFile();
                ApplicationFramework.getInstance().getSerializer().loadTemplate(file);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
