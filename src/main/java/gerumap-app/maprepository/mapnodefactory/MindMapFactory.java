package dsw.gerumap.app.maprepository.mapnodefactory;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.MindMap;

public class MindMapFactory extends MapNodeFactory {

    private static int mindMapNo = 1;

    @Override
    public MapNode createNode(MapNode selectedNode) {
        return new MindMap("MindMap " + mindMapNo++, selectedNode);
    }
}
