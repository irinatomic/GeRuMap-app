package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.maprepository.composite.*;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.mapnodefactory.*;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.*;
import java.util.Enumeration;

@Getter
@Setter
public class MapTreeImplementation implements MapTree {

    private MapTreeView treeView;
    private DefaultTreeModel treeModel;
    private MapTreeNode root;

    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        MapTreeNode root = new MapTreeNode(projectExplorer);
        this.root = root;
        treeModel = new DefaultTreeModel(root);
        treeView = new MapTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(MapTreeNode parent, MapNode childForTemplate) {
        if (!(parent.getMapNode() instanceof MapNodeComposite))
            return;

        if(parent.getMapNode() instanceof MindMap){
            int size = ((MindMap) parent.getMapNode()).getChildren().size();
            MapNode lastChild = ((MindMap) parent.getMapNode()).getChildren().get(size - 1);
            MapTreeNode newChildWrapper = new MapTreeNode(lastChild);
            parent.add(newChildWrapper);
            //parent.getChildren().add(newChildWrapper);
        } else {
            if(childForTemplate != null){
                MapTreeNode childWrapper = new MapTreeNode(childForTemplate);
                parent.add(childWrapper);
                parent.getChildren().add(childWrapper);
                ((MapNodeComposite) parent.getMapNode()).addChild(childForTemplate);
                return;
            }

            MapNode child = createChild(parent.getMapNode());
            if(child == null) return;
            MapTreeNode childWrapper = new MapTreeNode(child);
            parent.add(childWrapper);
            parent.getChildren().add(childWrapper);
            ((MapNodeComposite) parent.getMapNode()).addChild(child);
        }

        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeChild(MapTreeNode child) {
        MapNodeComposite parentOfSelected = null;
        if(child.getMapNode().getParent() instanceof MapNodeComposite) {
            parentOfSelected = (MapNodeComposite) child.getMapNode().getParent();
            parentOfSelected.removeChild(child.getMapNode());                           //izbrisano iz MapNoda
        }

        if(parentOfSelected == null) return;
        DefaultMutableTreeNode selectedTreeNode = (DefaultMutableTreeNode) treeView.getSelectionPath().getLastPathComponent();
        treeModel.removeNodeFromParent(selectedTreeNode);
    }


    @Override
    public MapTreeNode getSelectedNode() {
        return (MapTreeNode) treeView.getLastSelectedPathComponent();
    }

    @Override
    public void loadProject(Project node) {
        MapTreeNode loadProject = new MapTreeNode(node);
        root.add(loadProject);

        ((MapNodeComposite) root.getMapNode()).addChild(node);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    private MapNode createChild(MapNode parent) {
        MapNodeFactory nodeFactory = FactoryUtils.getFactory(parent);
        return nodeFactory.getNode(parent);
    }

    public TreePath findPathToNode(MapTreeNode root, MapNode currMapNode) {
        //pretrazivanje stabla rekurzivno
        Enumeration<TreeNode> element = root.depthFirstEnumeration();
        while (element.hasMoreElements()) {
            MapTreeNode node = (MapTreeNode) element.nextElement();
            if (node.getMapNode().equals(currMapNode)) {
                return new TreePath(node.getPath());
            }
        }
        return null;
    }

    public void deleteInTree(MapNode child){
        //trazimo put to trenutne mape, selektujemo trenutnu mapu -> dodajemo wrapper u tree -> trigeruje se repaint drveta
        MapTree ourTree = MainFrame.getInstance().getMapTree();
        MapTreeNode root = ((MapTreeImplementation)ourTree).getRoot();
        TreePath pathToCurrMap = ((MapTreeImplementation)MainFrame.getInstance().getMapTree()).findPathToNode(root, child);

        ((MapTreeImplementation)ourTree).getTreeView().setSelectionPath(pathToCurrMap);
        System.out.println("TRee " + ourTree.getSelectedNode());
        ourTree.removeChild(ourTree.getSelectedNode());
    }

    public void addToTree(MapNode parent, MapNode child){
        //trazimo put to trenutne mape, selektujemo trenutnu mapu -> dodajemo wrapper u tree -> trigeruje se repaint drveta
        MapTree ourTree = MainFrame.getInstance().getMapTree();
        MapTreeNode root = ((MapTreeImplementation)ourTree).getRoot();
        TreePath pathToCurrMap = ((MapTreeImplementation)MainFrame.getInstance().getMapTree()).findPathToNode(root, parent);

        ((MapTreeImplementation)ourTree).getTreeView().setSelectionPath(pathToCurrMap);
        ourTree.addChild(ourTree.getSelectedNode(), child);
    }
}
