package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.WorkSpaceImplementation;
import dsw.gerumap.app.gui.swing.workspace.view.ProjectView;
import dsw.gerumap.app.interfaces.ApplicationFramework;
import dsw.gerumap.app.maprepository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveProjectAction extends AbstractRudokAction{

    public SaveProjectAction(){
        putValue(NAME, "Save Project");
        putValue(SHORT_DESCRIPTION, "Save project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        ProjectView pv = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getProjectView();
        Project project = (Project) pv.getProject();
        if(project == null) return;

        File projectFile = null;
        if (!project.isChanged()) return;
        project.setChanged(false);

        project.setParent(null);
        if (project.getFilePath() == null || project.getFilePath().trim().equals("")) {
            if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                projectFile = jfc.getSelectedFile();
                project.setFilePath(projectFile.getPath());
            }
        }
        ApplicationFramework.getInstance().getSerializer().saveProject(project);
    }
}
