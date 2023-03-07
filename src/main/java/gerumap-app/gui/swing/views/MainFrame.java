package dsw.gerumap.app.gui.swing.views;

import dsw.gerumap.app.gui.swing.controller.ActionManager;
import dsw.gerumap.app.gui.swing.tree.MapTree;
import dsw.gerumap.app.gui.swing.tree.MapTreeImplementation;
import dsw.gerumap.app.gui.swing.tree.controler.MouseEventDemo;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.gui.swing.workspace.*;
import dsw.gerumap.app.interfaces.ApplicationFramework;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MainFrame extends JFrame {

    private static MainFrame instance;
    private ActionManager actionManager;
    private JMenuBar menu;
    private JToolBar toolBar;
    private JToolBar graphicToolBar;
    private MapTree mapTree;
    private IFWorkspace workspace;

    private MainFrame(){ }

    private void initialise(){
        actionManager = new ActionManager();
        mapTree = new MapTreeImplementation();
        workspace = new WorkSpaceImplementation();
        initialiseGUI();
    }

    private void initialiseGUI(){

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize (screenWidth/2, screenHeight/2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("GuRuMap App");

        menu = new MyMenuBar();
        setJMenuBar(menu);

        toolBar = new Toolbar();
        add(toolBar, BorderLayout.NORTH);

        //West - desna strana (workspace)
        JPanel rightPanel = workspace.generateWorkspace();

        //West - leva strana (ima mouse listener)
        ProjectExplorer pe = ApplicationFramework.getInstance().getMapRepository().getProjectExplorer();
        MapTreeView jTreeProjectExplorer = mapTree.generateTree(pe);
        pe.addSubscriber(jTreeProjectExplorer);
        jTreeProjectExplorer.addMouseListener(new MouseEventDemo(jTreeProjectExplorer));
        JScrollPane scroll = new JScrollPane(jTreeProjectExplorer);
        scroll.setMaximumSize(new Dimension(200, 150));

        JSplitPane split = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, scroll, rightPanel);
        getContentPane().add(split, BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);

        //East - toolbar za graficke izmene
        graphicToolBar = new GraphicToolbar();
        getContentPane().add(graphicToolBar, BorderLayout.EAST);
    }

    public static MainFrame getInstance(){
        if(instance == null){
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }
}
