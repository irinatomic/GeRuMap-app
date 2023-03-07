package dsw.gerumap.app.maprepository.mapnodefactory;

import dsw.gerumap.app.maprepository.composite.MapNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MapNodeFactory implements IFMapNodeFactory {

    @Override
    public MapNode getNode(MapNode selectedNode){
        return createNode(selectedNode);
    }

    public abstract MapNode createNode(MapNode selectedNode);
}
