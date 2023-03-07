package dsw.gerumap.app.maprepository;

import dsw.gerumap.app.interfaces.MapRepository;
import dsw.gerumap.app.maprepository.composite.*;
import dsw.gerumap.app.maprepository.mapnodefactory.IFMapNodeFactory;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapRepositoryImplementation implements MapRepository{

    private ProjectExplorer projectExplorer;
    private IFMapNodeFactory ifMapNodeFactory;

    public MapRepositoryImplementation() {
        projectExplorer = new ProjectExplorer("My Project Explorer");
    }

    @Override
    public void addChild(MapNodeComposite parent, MapNode child) {
    }

}
