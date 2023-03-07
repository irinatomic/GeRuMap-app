package dsw.gerumap.app.maprepository.mapnodefactory;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.Project;

public class ProjectFactory extends MapNodeFactory {

    private static int projectNo = 1;

    @Override
    public MapNode createNode(MapNode selectedNode) {
        return new Project("Project " + projectNo++, selectedNode, "Autor");
    }
}