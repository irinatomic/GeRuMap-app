package dsw.gerumap.app;

import dsw.gerumap.app.gui.swing.*;
import dsw.gerumap.app.interfaces.*;
import dsw.gerumap.app.logger.FileLogger;
import dsw.gerumap.app.maprepository.MapRepositoryImplementation;
import dsw.gerumap.app.messages.MessageGeneratorImplementation;
import dsw.gerumap.app.serializer.GsonSerializer;

public class AppCore {

    private static AppCore instance;
    public AppCore(){

    }
    public static AppCore getInstance(){
        if (instance == null){
            instance = new AppCore();
        }
        return instance;
    }

    public static void main(String[] args){
        Gui gui = new SwingGui();
        MapRepository mapRepository = new MapRepositoryImplementation();
        MessageGenerator messageGenerator = new MessageGeneratorImplementation();
        Logger logger = new FileLogger();
        Serializer serializer = new GsonSerializer();

        ApplicationFramework appFramework = ApplicationFramework.getInstance();
        appFramework.initialise(gui, mapRepository, messageGenerator, logger, serializer);
        appFramework.run();
    }
}
