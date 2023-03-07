package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.commands.DeleteCommand;
import dsw.gerumap.app.gui.swing.painters.Painter;
import dsw.gerumap.app.gui.swing.workspace.view.*;
import dsw.gerumap.app.interfaces.ApplicationFramework;
import dsw.gerumap.app.maprepository.implementation.*;
import dsw.gerumap.app.messages.IllegalEvent;

import java.awt.*;
import java.util.List;

public class DeleteState extends State{
    @Override
    public void mousePressed(Point e, ProjectView projectView, MindMap currMindMap) {
        List<Painter> selected = projectView.getSelectedComponents();

        if(selected.isEmpty()) {
            Painter sp = getClicked(e, projectView, currMindMap);
            if(sp == null){
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.NODE_NOT_SELECTED); return;
            }
            sp.setSelected(true);
            selected.add(sp);
        }

        DeleteCommand dc = new DeleteCommand(projectView, selected);
        MapView currMapView = (MapView) projectView.getTabbedPane().getSelectedComponent();
        currMapView.getCommandManager().addCommand(dc);

        projectView.deselect();
    }

    @Override
    public void mouseReleased(Point e, ProjectView projectView, MindMap currMindMap) {
        //NE TREBA NAM
    }

    @Override
    public void mouseDragged(Point e, ProjectView projectView, MindMap currMindMap) {
        //NE TREBA NAM
    }

    private Painter getClicked(Point e, ProjectView projectView, MindMap currMindMap){
        for(Painter p : projectView.getPaintersForMap().get(currMindMap)){
            if(p.elementAt(e.x, e.y))
                return p;
        }
        return null;
    }
}