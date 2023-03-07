package dsw.gerumap.app.serializer;

import com.google.gson.*;
import dsw.gerumap.app.gui.swing.painters.Painter;
import dsw.gerumap.app.gui.swing.painters.*;
import dsw.gerumap.app.gui.swing.tree.MapTreeImplementation;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.WorkSpaceImplementation;
import dsw.gerumap.app.gui.swing.workspace.view.ProjectView;
import dsw.gerumap.app.interfaces.*;
import dsw.gerumap.app.maprepository.composite.*;
import dsw.gerumap.app.maprepository.implementation.*;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.geom.Ellipse2D;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GsonSerializer implements Serializer {

    private final Gson gson = new GsonBuilder().registerTypeAdapter(MapNode.class, new ProjectDeserializer()).create();

    @Override
    public Project loadProject(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            Project project = gson.fromJson(fileReader, Project.class);

            //reset the parent properties
            for(MapNode mindMap : project.getChildren()){
                mindMap.setParent(project);
                MapNodeComposite mpc = (MapNodeComposite) mindMap;
                for(MapNode element : mpc.getChildren())
                    element.setParent(mindMap);
            }

            //add it to tree
            MapTreeView treeView = ((MapTreeImplementation)MainFrame.getInstance().getMapTree()).getTreeView();
            MapTreeNode root = (MapTreeNode) treeView.getModel().getRoot();
            TreePath path = new TreePath(root.getPath());
            treeView.setSelectionPath(path);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);

            //add project as child to project explorer (in model)
            ((MapNodeComposite)root.getMapNode()).getChildren().add(project);

            //open it in project view -> create the painters
            ProjectView projectView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getProjectView();
            projectView.updateWorkspace(project);

            //repaint all maps
            for(MapNode mindMap : project.getChildren()){
                repaintTheMindMap(projectView, (MindMap) mindMap);
            }

            return project;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveProject(Project project) {
        try (FileWriter writer = new FileWriter(project.getFilePath())) {
            gson.toJson(project, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadTemplate(File file){
        ProjectView projectView = ((WorkSpaceImplementation) MainFrame.getInstance().getWorkspace()).getProjectView();

        try (FileReader fileReader = new FileReader(file)){
            MindMap mindMap = gson.fromJson(fileReader, MindMap.class);

            //add mindMap to currently opened project
            Project currProject = (Project)projectView.getProject();
            if(currProject == null) return;
            mindMap.setParent(currProject);
            projectView.updateWorkspace(currProject);

            //add to tree
            MapTreeImplementation mti = (MapTreeImplementation)MainFrame.getInstance().getMapTree();
            mti.addToTree(currProject, mindMap);

            for(MapNode element : mindMap.getChildren())
                element.setParent(mindMap);

            repaintTheMindMap(projectView, mindMap);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveTemplate(MindMap map, String templateName){
        map.setTemplate(true);
        map.setName(templateName);
        try {
            FileWriter fw = new FileWriter("src/main/resources/templates/" + templateName + ".json", false);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(gson.toJson(map));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void repaintTheMindMap(ProjectView projectView, MindMap map){
        List<Element> assocs = new ArrayList<>();
        //first add all the TopicPainters
        for(MapNode element : map.getChildren()){
            Painter p;
            if(element instanceof Association) {
                assocs.add((Element) element);
                p = new AssociationPainter((Element) element);
            } else {
                Topic t = (Topic) element;
                p = new TopicPainter((Element) element);
                Ellipse2D ellipse = new Ellipse2D.Double(t.getX(), t.getY(), t.getWidth(), t.getHeight());
                p.setShape(ellipse);
            }
            projectView.getPaintersForMap().get(map).add(p);
        }
        projectView.repaintAll();

        //add all AssociationPainters and redetermine their end topic
        for(Element e : assocs)
            redetermineTopicForAssoc(projectView.getPaintersForMap().get(map), e);
        projectView.repaintAll();
    }


    private void redetermineTopicForAssoc(List<Painter> painters, Element assoc){
        Association a = (Association) assoc;
        for(Painter p : painters){
            if(!(p instanceof TopicPainter)) return;
            TopicPainter tp = (TopicPainter) p;
            Topic t = (Topic) tp.getElement();
            if(tp.elementAt((int)a.getXFrom(), (int)a.getYFrom()))
                a.setFrom(t);
            if (tp.elementAt((int)a.getXTo(), (int)a.getYTo()))
                a.setTo(t);
        }
    }
}