package dsw.gerumap.app.gui.swing.commands;

import dsw.gerumap.app.gui.swing.painters.AssociationPainter;
import dsw.gerumap.app.gui.swing.painters.Painter;
import dsw.gerumap.app.gui.swing.workspace.view.*;
import dsw.gerumap.app.interfaces.ApplicationFramework;
import dsw.gerumap.app.maprepository.implementation.*;
import dsw.gerumap.app.messages.IllegalEvent;

public class NewAssociationCommand extends AbstractCommand{

    private ProjectView projectView;
    private MindMap currMindMap;
    private Painter thisAssocPainter;
    private Topic startTopic;
    private Topic endTopic;

    public NewAssociationCommand(ProjectView projectView, MindMap currMindMap, Topic startTopic, Topic endTopic){
        this.projectView = projectView;
        this.currMindMap = currMindMap;
        this.startTopic = startTopic;
        this.endTopic = endTopic;
    }

    @Override
    public void doCommand() {
        //AKO JE PRVI PUT
        if(thisAssocPainter == null){
            String name = startTopic.getName() + "-" + endTopic.getName();
            Association a = new Association(name, currMindMap, startTopic);
            a.setTo(endTopic);
            thisAssocPainter = new AssociationPainter(a);

            //ako vec postoji takva veza
            if(projectView.getPaintersForMap().get(currMindMap).contains(thisAssocPainter)){
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.ASSOCIATION_ALREADY_EXISTS);
                ((MapView)projectView.getTabbedPane().getSelectedComponent()).getCommandManager().getCommands().remove(this);
                return;
            }

            //Ako veza ide iz pojma u isti pojam
            if(a.getFrom().equals(a.getTo())){
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.ASSOCIATION_INTO_ITSELF);
                ((MapView)projectView.getTabbedPane().getSelectedComponent()).getCommandManager().getCommands().remove(this);
                return;
            }
        }
        projectView.addPainterForCurrent(thisAssocPainter);
    }

    @Override
    public void undoCommand() {
        projectView.deletePainterForCurrent(thisAssocPainter);
    }
}
