package dsw.gerumap.app.gui.swing.views;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class GraphicToolbar extends JToolBar {

    public GraphicToolbar(){
        super(VERTICAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getNewTopicAction());
        add(MainFrame.getInstance().getActionManager().getNewAssociationAction());
        add(MainFrame.getInstance().getActionManager().getSelectAction());
        add(MainFrame.getInstance().getActionManager().getMoveAction());
        add(MainFrame.getInstance().getActionManager().getDeleteAction());
        add(MainFrame.getInstance().getActionManager().getZoomInAction());
        add(MainFrame.getInstance().getActionManager().getZoomOutAction());
        add(MainFrame.getInstance().getActionManager().getCentralTopicAction());
        //add(Box.createVerticalGlue());
    }
}
