package dsw.gerumap.app.interfaces;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationFramework{

    protected Gui gui;
    protected MapRepository mapRepository;
    protected MessageGenerator messageGenerator;
    protected Logger logger;
    protected Serializer serializer;
    private static ApplicationFramework instance;

    private ApplicationFramework(){ }
    public static ApplicationFramework getInstance(){
        if(instance == null){
            instance = new ApplicationFramework();
        }
        return instance;
    }

    public void run(){
        this.gui.start();
    }

    public void initialise(Gui gui, MapRepository mapRepository, MessageGenerator messageGenerator, Logger logger, Serializer serializer){
        this.gui = gui;                                                     
        this.mapRepository = mapRepository;
        this.messageGenerator = messageGenerator;
        this.logger = logger;
        this.serializer = serializer;

        messageGenerator.addSubscriber(logger);
        messageGenerator.addSubscriber(gui);
    }
}
