package dsw.gerumap.app.gui.swing.workspace.view;

import dsw.gerumap.app.gui.swing.painters.Painter;
import dsw.gerumap.app.gui.swing.state.StateManager;
import dsw.gerumap.app.gui.swing.tree.*;
import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.maprepository.composite.*;
import dsw.gerumap.app.maprepository.implementation.*;
import dsw.gerumap.app.observer.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

@Getter
@Setter
//SUBSCRIBER NA PROJEKAT, IMA ULOGU MEDIJATORA
//ima listu Paintera (svaki painter kao polje ima element koji crta)
public class ProjectView extends JPanel implements IFSubscriber {

    private JLabel lProjectName = new JLabel();
    private JLabel lAuthor = new JLabel();
    private String projectName = "";
    private String author = "";
    private MapNode project = null;                    //projekat koji trenutno prikazujemo
    private JTabbedPane tabbedPane = new JTabbedPane();
    private List<MapView> tabs = new ArrayList<>();
    Map<MindMap, List<Painter>> paintersForMap = new HashMap<>();
    List<Painter> selectedComponents = new ArrayList<>();
    private StateManager stateManager;

    public ProjectView() {
        stateManager = new StateManager();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(lProjectName);
        add(lAuthor);
        add(tabbedPane);
    }

    public void updateWorkspace(MapNode selectedProject){
        //UKLANJAMO ProjectView KAO SUBSCRIBERA NA STARI PROJEKAT I SUBSCRIBUJEMO GA NA NOVI
        if(this.project != null) this.project.removeSubscriber(this);
        selectedProject.addSubscriber(this);

        //PRVO BRISEMO SVE PRETHODNE TABOVE DA NAM SE NE BI DODAVALI NA STARE
        tabbedPane.removeAll();
        tabs.clear();

        //POSTAVLJAMO PROJEKAT, NASLOV I AUTORA PROJEKTA
        this.project = selectedProject;
        this.projectName = selectedProject.getName();
        this.author = ((Project) selectedProject).getAuthor();
        this.lProjectName.setText(selectedProject.getName());
        this.lAuthor.setText(author);

        //POPUNJAVAMO LISTU MAPA TJ TABOVA I SUBSCRIBUJEMO TAJ TAB NA MAPU, MAPU DODAJEMO KAO KLJUC AKO JE NEMA
        for (MapNode child : ((MapNodeComposite) selectedProject).getChildren()) {
            MapView tab = new MapView((MindMap) child);
            child.addSubscriber(tab);
            tabs.add(tab);

            paintersForMap.putIfAbsent((MindMap) child, new ArrayList<>());
        }

        //ZA SVAKU MAPU IZ LISTE MAPA PRAVIMO TAB
        for(MapView tab : tabs){
            tab.setLayout(new GridLayout(1, 1));
            tabbedPane.addTab(tab.getMindMap().getName(), null, tab);
        }
    }

    public void deselect(){
        for(Painter p : selectedComponents)
            p.setSelected(false);
        selectedComponents.clear();
    }

    @Override
    public void update(Object notification) {
        SwingUtilities.updateComponentTreeUI(this);    //updates the tree on the left (just in case here as well)

        //u zavisnosti od toga koja akcija je prosledjena
        if(notification.equals(Notification.ADD_AUTHOR)){
            this.author = ((Project)project).getAuthor();
            this.lAuthor.setText(author);
        }
        else if(notification.equals(Notification.RENAME)){
            this.projectName = project.getName();
            this.lProjectName.setText(projectName);
        }
        else if(notification.equals(Notification.NEW)){
            MapNodeComposite mpcProject = (MapNodeComposite)project;
            MapNode newMindMap = mpcProject.getChildren().get(mpcProject.getChildren().size() - 1);         //dohvatamo novu mapu
            paintersForMap.putIfAbsent((MindMap) newMindMap, new ArrayList<>());

            MapView tab = new MapView((MindMap) newMindMap);
            newMindMap.addSubscriber(tab);
            tabs.add(tab);                      //novu mapu dodajemo u listu tabs

            JComponent panelForMap = new JPanel();
            panelForMap.setLayout(new GridLayout(1, 1));
            tabbedPane.addTab(tab.getMindMap().getName(), null, tab);   //pravimo prikaz za novu mpu -> JTab
        }
        else if(notification.equals(Notification.REMOVE)) {
            this.project = null;
            projectName = "";   author = "";
            lProjectName.setText(projectName);  lAuthor.setText(author);
            tabs.clear();  getTabbedPane().removeAll();
        }
        else if(notification.equals(Notification.REPAINT)) {
            MapView currMapView = tabs.get(tabbedPane.getSelectedIndex());
            currMapView.setPainters(paintersForMap.get(currMapView.getMindMap()));
        }
        this.revalidate();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(tabbedPane == null || tabs == null || tabs.size() == 0) return;
        MapView currMapView = (MapView) tabbedPane.getSelectedComponent();
        currMapView.setPainters(paintersForMap.get(currMapView.getMindMap()));
        currMapView.revalidate();
        currMapView.repaint();
    }

    public void deletePainterForCurrent(Painter painter){
        if(painter.getElement() == null) {
            repaint();
            return;
        }

        MindMap currMindMap = ((MapView)tabbedPane.getSelectedComponent()).getMindMap();
        currMindMap.removeChild(painter.getElement());
        paintersForMap.get(currMindMap).remove(painter);
        ((MapTreeImplementation)MainFrame.getInstance().getMapTree()).deleteInTree(painter.getElement());
        repaint();
    }

    public void addPainterForCurrent(Painter painter){
        MindMap currMindMap = ((MapView)tabbedPane.getSelectedComponent()).getMindMap();
        paintersForMap.get(currMindMap).add(painter);
        currMindMap.addChild(painter.getElement());
        ((MapTreeImplementation)MainFrame.getInstance().getMapTree()).addToTree(currMindMap, null);
        repaint();
    }

    public void repaintAll(){
        for(MapView mapView : tabs){
            tabbedPane.setSelectedComponent(mapView);
            mapView.getPainters().addAll(paintersForMap.get(mapView.getMindMap()));
            mapView.repaint();
        }
    }
}