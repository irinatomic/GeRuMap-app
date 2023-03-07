package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.WorkSpaceImplementation;
import dsw.gerumap.app.gui.swing.workspace.view.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PictureAction extends  AbstractRudokAction{

    public PictureAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/camera.png"));
        putValue(NAME, "Save picture");
        putValue(SHORT_DESCRIPTION, "Save map as picture");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectView projectView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getProjectView();
        MapView currMapView = (MapView) projectView.getTabbedPane().getSelectedComponent();

        JFileChooser jfc = new JFileChooser();
        if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File filePicture = jfc.getSelectedFile();

            BufferedImage image = currMapView.createImage();
            System.out.println(image);

            try {
                ImageIO.write(image, "png", new File(filePicture.getAbsolutePath()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
