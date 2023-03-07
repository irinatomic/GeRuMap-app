package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.commands.NewAssociationCommand;
import dsw.gerumap.app.gui.swing.painters.*;
import dsw.gerumap.app.gui.swing.painters.Painter;
import dsw.gerumap.app.gui.swing.workspace.view.*;
import dsw.gerumap.app.interfaces.ApplicationFramework;
import dsw.gerumap.app.maprepository.implementation.*;
import dsw.gerumap.app.messages.IllegalEvent;

import java.awt.*;
import java.util.List;

public class NewAssociationState extends State{

    private AssociationPainter ap = null;
    @Override
    public void mousePressed(Point e, ProjectView projectView, MindMap currMindMap) {
        List<Painter> currMindMapPainters = projectView.getPaintersForMap().get(currMindMap);

        //Ako zapocinjemo vezu u pojmu: pravimo novu vezu, painter za tu vezu - i dodajemo ga u spisak paintera za trenutnu mapu
        for(Painter painter : currMindMapPainters){
            if(painter instanceof TopicPainter && painter.elementAt(e.x, e.y)){
                Topic from = (Topic)painter.getElement();
                Association newA = new Association("veza: " + from.getName(), currMindMap, from);
                AssociationPainter newAP = new AssociationPainter(newA);
                this.ap = newAP;
                currMindMapPainters.add(newAP);
                projectView.getPaintersForMap().replace(currMindMap, currMindMapPainters);
                return;
            }
        }


        ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.ASSOCIATION_MUST_BEGIN_IN_TOPIC);
    }

    @Override
    public void mouseReleased(Point e, ProjectView projectView, MindMap currMindMap) {
        List<Painter> currMindMapPainters = projectView.getPaintersForMap().get(currMindMap);
        currMindMapPainters.remove(currMindMapPainters.size() - 1);

        //Ako zavrsavamo vezu u pojmu
        for(Painter painter : currMindMapPainters){
            if(painter instanceof TopicPainter && painter.elementAt(e.x, e.y)){
                Topic startTopic = ((Association)ap.getElement()).getFrom();
                Topic endTopic = (Topic) painter.getElement();
                NewAssociationCommand nac = new NewAssociationCommand(projectView, currMindMap, startTopic, endTopic);

                MapView currMapView = (MapView) projectView.getTabbedPane().getSelectedComponent();
                currMapView.getCommandManager().addCommand(nac);
                this.ap = null;
                return;
            }
        }

        ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.ASSOCIATION_MUST_END_IN_TOPIC);
    }

    @Override
    public void mouseDragged(Point e, ProjectView projectView, MindMap currMindMap) {
        //nas trenutni painter za asocijaciju
        AssociationPainter currAP = ap;
        Association currA = (Association) currAP.getElement();
        currA.setXTo(e.getX());
        currA.setYTo(e.getY());

        //da bi se trigerovao repaint
        currMindMap.addChild(currA);
        currMindMap.removeChild(currA);
    }
}