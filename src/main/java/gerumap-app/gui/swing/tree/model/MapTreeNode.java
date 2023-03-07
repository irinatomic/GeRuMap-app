package dsw.gerumap.app.gui.swing.tree.model;

import dsw.gerumap.app.maprepository.composite.MapNode;
import lombok.Getter;
import lombok.Setter;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MapTreeNode extends DefaultMutableTreeNode {

    private MapNode mapNode;
    private List<MapTreeNode> children = new ArrayList<>();

    public MapTreeNode(MapNode mapNode) {
        this.mapNode = mapNode;
    }

    @Override
    public String toString() {
        return mapNode.getName();
    }       //da bi se videlo u stablu sa leve strane

}
