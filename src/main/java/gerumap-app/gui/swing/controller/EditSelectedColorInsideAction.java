package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.painters.Painter;
import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.WorkSpaceImplementation;
import dsw.gerumap.app.gui.swing.workspace.view.ProjectView;
import dsw.gerumap.app.interfaces.ApplicationFramework;
import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.messages.IllegalEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class EditSelectedColorInsideAction extends AbstractRudokAction{

    public EditSelectedColorInsideAction(){
        putValue(SMALL_ICON, loadIcon("/images/editSelectedColor.png"));
        putValue(NAME, "Change Inside Color");
        putValue(SHORT_DESCRIPTION, "Change Inside Color");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectView projectView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getProjectView();
        List<Painter> selectedPainters = projectView.getSelectedComponents();
        if(selectedPainters == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.NODE_NOT_SELECTED);
            return;
        }
        Color color = JColorChooser.showDialog(new JFrame(), "Select a color", Color.WHITE);

        String R = Integer.toHexString(color.getRed());
        String G = Integer.toHexString(color.getGreen());
        String B = Integer.toHexString(color.getBlue());
        if(R.length() == 1) R = "0" + R;
        if(G.length() == 1) G = "0" + G;
        if(B.length() == 1) B = "0" + B;
        String hexColor = "0x" + R + G + B;

        for (Painter p: selectedPainters) {
            Element selectedElement = p.getElement();
            selectedElement.setColourInside(hexColor);
        }
    }
}
