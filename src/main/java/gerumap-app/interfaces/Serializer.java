package dsw.gerumap.app.interfaces;

import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;

import java.io.File;

public interface Serializer {

    Project loadProject (File file);
    void saveProject (Project node);
    void loadTemplate(File file);
    void saveTemplate(MindMap mindMap, String templateName);
}
