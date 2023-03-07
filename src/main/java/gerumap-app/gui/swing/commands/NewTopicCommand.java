package dsw.gerumap.app.gui.swing.commands;

import dsw.gerumap.app.gui.swing.painters.*;
import dsw.gerumap.app.gui.swing.painters.Painter;
import dsw.gerumap.app.gui.swing.workspace.view.ProjectView;
import dsw.gerumap.app.interfaces.ApplicationFramework;
import dsw.gerumap.app.maprepository.implementation.*;
import dsw.gerumap.app.messages.IllegalEvent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NewTopicCommand extends AbstractCommand{

    private ProjectView projectView;
    private MindMap currMindMap;
    private Painter thisTopicPainter;
    private List<Painter> assToTopic;
    private Point topicPoint;

    public NewTopicCommand(Point topicPoint, ProjectView projectView, MindMap currMindMap){
        this.topicPoint = topicPoint;
        this.projectView = projectView;
        this.currMindMap = currMindMap;
        assToTopic = new ArrayList<>();
    }

    //Dodaje jedan Topic
    @Override
    public void doCommand() {
        //AKO NIJE PRVI PUT
        if(thisTopicPainter != null) {
            currMindMap.addChild(thisTopicPainter.getElement());
            projectView.addPainterForCurrent(thisTopicPainter);
            addAssociations();
        }

        //AKO JE PRVI PUT
        else{
            String topicString = JOptionPane.showInputDialog(new JFrame(), "Unesite pojam", "Pojam", JOptionPane.PLAIN_MESSAGE);
            if(topicString == null || topicString.trim().equals("")){
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.TOPIC_CANNOT_BE_EMPTY);
                return;
            }

            //new topic
            Topic newTopic = new Topic(topicString, currMindMap, topicPoint.x, topicPoint.y);
            currMindMap.addChild(newTopic);

            //new painter
            Painter painter = new TopicPainter(newTopic);
            thisTopicPainter = painter;
            projectView.addPainterForCurrent(painter);
        }
    }

    //Brise jedan Topic
    @Override
    public void undoCommand() {
        assToTopic = findAssocToTopic();
        for (Painter p : assToTopic)
            projectView.deletePainterForCurrent(p);
        projectView.deletePainterForCurrent(thisTopicPainter);
    }

    private List<Painter> findAssocToTopic(){
        List<Painter> assToTopic = new ArrayList<>();
        List<Painter> currMapViewPainters = projectView.getPaintersForMap().get(currMindMap);
        for(Painter p : currMapViewPainters){
            if(p instanceof AssociationPainter){
                Association a = (Association) p.getElement();
                Topic t = (Topic) thisTopicPainter.getElement();
                if(a.getFrom().equals(t) || a.getTo().equals(t))
                    assToTopic.add(p);
            }
        }
        return assToTopic;
    }

    private void addAssociations(){
        for(Painter p : assToTopic){
            Association a = (Association) p.getElement();
            if(currMindMap.getChildren().contains(a.getFrom()) && currMindMap.getChildren().contains(a.getTo()))
                projectView.addPainterForCurrent(p);
        }
    }
}