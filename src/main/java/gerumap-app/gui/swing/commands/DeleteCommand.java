package dsw.gerumap.app.gui.swing.commands;

import dsw.gerumap.app.gui.swing.painters.Painter;
import dsw.gerumap.app.gui.swing.painters.*;
import dsw.gerumap.app.gui.swing.workspace.view.*;
import dsw.gerumap.app.maprepository.implementation.*;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends AbstractCommand{

    private ProjectView projectView;
    private MapView currMapView;
    private List<Painter> selected;
    private List<Painter> deleted;

    public DeleteCommand(ProjectView projectView, List<Painter> selected) {
        this.projectView = projectView;
        this.currMapView = (MapView) projectView.getTabbedPane().getSelectedComponent();
        this.selected = selected;
        deleted = new ArrayList<>();
    }


    //Prvo brisemo svaki pojam i onda veze ka njemu -> izbrisano cuvamo u listi 'deleted'
    @Override
    public void doCommand() {
        List<Painter> deletedTopics = new ArrayList<>();
        for(Painter p : selected){
            if(p instanceof TopicPainter) {
                deleted.add(p);
                deletedTopics.add(p);
            }
        }

        for(Painter p : deletedTopics){
            deleteAssocToTopic(p.getElement());
            projectView.deletePainterForCurrent(p);
        }
    }

    //Prvo vracamo pojmove i onda veze za koje postoje pojmovi na mapi
    @Override
    public void undoCommand() {
        for(Painter p : deleted){
            if(p instanceof TopicPainter)
                projectView.addPainterForCurrent(p);
        }

        List<Topic> topicsOnWorkspcae = new ArrayList<>();
        for(Painter p : projectView.getPaintersForMap().get(currMapView.getMindMap())){
            if(p instanceof TopicPainter)
                topicsOnWorkspcae.add((Topic) p.getElement());
        }

        for(Painter p : deleted){
            if(p instanceof TopicPainter) continue;
            Association a = (Association) p.getElement();
            if(topicsOnWorkspcae.contains(a.getFrom()) && topicsOnWorkspcae.contains(a.getTo()))
                projectView.addPainterForCurrent(p);
        }
    }

    private void deleteAssocToTopic(Element e){
        Topic t = (Topic)e;
        List<Painter> paintersForCurr = projectView.getPaintersForMap().get(currMapView.getMindMap());
        List<Painter> assocToDelete = new ArrayList<>();

        for(Painter p : paintersForCurr){
            if(p instanceof AssociationPainter){
                Association a = (Association) p.getElement();
                if(a.getFrom().equals(t) || a.getTo().equals(t)){
                    deleted.add(p);
                    assocToDelete.add(p);
                }
            }
        }

        for(Painter p : assocToDelete)
            projectView.deletePainterForCurrent(p);
    }
}