package dsw.gerumap.app.maprepository.mapnodefactory;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;

public class FactoryUtils {

    public static MapNodeFactory getFactory(MapNode selectedNode){

        if(selectedNode instanceof ProjectExplorer)
            return new ProjectFactory();
        else if(selectedNode instanceof Project)
            return new MindMapFactory();

        return null;
    }
}
