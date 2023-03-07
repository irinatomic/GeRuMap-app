package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.painters.Painter;
import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.WorkSpaceImplementation;
import dsw.gerumap.app.gui.swing.workspace.view.*;
import dsw.gerumap.app.maprepository.implementation.*;

import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

public class CentralTopicAction extends AbstractRudokAction{

    public CentralTopicAction(){
        putValue(SMALL_ICON, loadIcon("/images/centralTopic.png"));
        putValue(NAME, "Central Topic");
        putValue(SHORT_DESCRIPTION, "Set a central topic");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectView projectView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getProjectView();
        int selectedMapIndex = projectView.getTabbedPane().getSelectedIndex();
        MapView currMapView = projectView.getTabs().get(selectedMapIndex);
        List<Painter> selected = projectView.getSelectedComponents();
        List<Painter> mapPainters = currMapView.getPainters();

        double selectedTopicX = 0;
        double selectedTopicY = 0;

        if (selected.size() != 1) return;
        Painter selectedTopic = selected.get(0);
        Element pomElem = selectedTopic.getElement();
        if (pomElem instanceof Topic){
            selectedTopicX = ((Topic) pomElem).getX();
            selectedTopicY = ((Topic) pomElem).getY();
        }
        List<Painter> row = new ArrayList<>();
        //lista Veza i lista Pojmova
        List<Association> listOfAssociation = new ArrayList<>();
        List<Topic> listOfTopic = new ArrayList<>();
        for (Painter p: mapPainters){
            if (p.getElement() instanceof Association){
                listOfAssociation.add((Association) p.getElement());
            }else{
                listOfTopic.add((Topic) p.getElement());
            }
        }

        selectedTopic.setChecked(true);
        row.add(selectedTopic);

        List<Painter> copyMapPainters = new ArrayList<>();
        copyMapPainters.addAll(mapPainters);

        for (Painter p: mapPainters){
            if (p.getElement() instanceof Topic){
                for (Painter ps: mapPainters){
                    Element elem = ps.getElement();
                    if (elem instanceof Association){
                        if (((Association) elem).getFrom().equals(p.getElement())){
                            if (!row.contains(p)) row.add(p);
                        }
                        if (((Association) elem).getTo().equals(p.getElement())) {
                            if (!row.contains(p)) row.add(p);
                        }
                    }
                }
            }
        }
        int trenutniNivoZaX = -1;
        List<Painter> copyRow = new ArrayList<>();
        copyRow.addAll(row);

        double grafLevel = 0;
        double numberOfConectedTopics = - 1;
        double nivo = 0;

        for (int q = 0; q < copyRow.size(); q++){
            Painter painterInRow = copyRow.get(q);
            Painter levelAtPainter = row.get(q);
            grafLevel++;

            numberOfConectedTopics = -1;

            if (painterInRow.getElement() instanceof Topic){
                for (int i = 0; i < copyMapPainters.size(); i++){
                    Painter p = copyMapPainters.get(i);
                    Element elem = p.getElement();
                    if (elem instanceof Association){
                        double fromX = ((Association) elem).getXFrom();
                        double fromY = ((Association) elem).getYFrom();
                        double toX = ((Association) elem).getXTo();
                        double toY = ((Association) elem).getYTo();

                        if (painterInRow.getElement().equals(((Association) elem).getFrom())){
                            Painter checkedPainter = mapPainters.get(i);
                            if(checkedPainter.isChecked()) continue;
                            checkedPainter.setChecked(true);
                            numberOfConectedTopics++;
                            for (int j = 0; j < copyMapPainters.size(); j++){
                                Painter cp = copyMapPainters.get(j);
                                Element checkElem = cp.getElement();
                                if (checkElem instanceof Topic){
                                    if (((Association) elem).getTo().equals(checkElem)) {
                                        double newTopicX = selectedTopicX + grafLevel * 100;
                                        double newTopicY = selectedTopicY + numberOfConectedTopics * 80;

                                        Painter changeKordTopic = mapPainters.get(j);
                                        if (changeKordTopic.getElement() instanceof Topic){
                                            ((Topic) changeKordTopic.getElement()).setX(newTopicX);
                                            ((Topic) changeKordTopic.getElement()).setY(newTopicY);
                                        }
                                    }
                                }
                            }
                        }

                        if (painterInRow.getElement().equals(((Association) elem).getTo())){
                            Painter checkedPainter = copyMapPainters.get(i);
                            if(checkedPainter.isChecked()) continue;
                            checkedPainter.setChecked(true);
                            numberOfConectedTopics++;
                            for (int j = 0; j < copyMapPainters.size(); j++){
                                Painter cp = mapPainters.get(j);
                                Element checkElem = cp.getElement();
                                if (checkElem instanceof Topic){
                                    if (((Association) elem).getFrom().equals(checkElem)) {
                                        double newTopicX = selectedTopicX + grafLevel * 100;
                                        double newTopicY = selectedTopicY + numberOfConectedTopics * 80;

                                        Painter changeKordTopic = mapPainters.get(j);
                                        if (changeKordTopic.getElement() instanceof Topic){
                                            ((Topic) changeKordTopic.getElement()).setX(newTopicX);
                                            ((Topic) changeKordTopic.getElement()).setY(newTopicY);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        projectView.repaint();
        for (int j = 0; j < copyMapPainters.size(); j++) {
            Painter p = mapPainters.get(j);
            p.setChecked(false);
            p.setGraflevel(0);
        }
    }
}