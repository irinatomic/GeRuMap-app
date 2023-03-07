package dsw.gerumap.app.gui.swing.controller;

import java.awt.event.ActionEvent;

public class ModifyAction extends AbstractRudokAction{

    public ModifyAction(){
        putValue(SMALL_ICON, loadIcon("/images/modify.png"));
        putValue(NAME, "Modify");
        putValue(SHORT_DESCRIPTION, "Modify");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //PITATI
    }
}
