package dsw.gerumap.app.messages;

import dsw.gerumap.app.interfaces.MessageGenerator;
import dsw.gerumap.app.observer.APublisher;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class MessageGeneratorImplementation extends APublisher implements MessageGenerator {

    @Override
    public void createMessage(IllegalEvent illegalEvent) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Message message = null;

        switch(illegalEvent){
            case AUTHOR_CANNOT_BE_EMPTY:
                message = new Message("Ime autora ne moze biti prazno", "ERROR", timestamp); break;
            case NAME_CANNOT_BE_EMPTY:
                message = new Message("Ime ne moze biti prazno", "ERROR", timestamp); break;
            case EXISTS_SAME_NAME_COMPONENT:
                message = new Message("Postoji komponenta na istom nivou istog imena", "ERROR", timestamp); break;
            case NODE_NOT_SELECTED:
                message = new Message("Niste selektovali nijednu komponentu", "INFORMATION", timestamp); break;
            case CANNOT_ADD_CHILD:
                message = new Message("Na selektovanu komponentu ne mozete dodati dete", "ERROR", timestamp); break;
            case CANNOT_DELETE_PROJECTEXPLORER:
                message = new Message("Ne mozete izbrisati ProjectExplorer", "ERROR", timestamp); break;
            case ONLY_PROJECT_CAN_HAVE_AUTHOR:
                message = new Message("Samo projekat moze imati autora", "ERROR", timestamp); break;
            case TOPIC_CANNOT_BE_EMPTY:
                message = new Message("Pojam ne moze biti prazan", "ERROR", timestamp); break;
            case ASSOCIATION_MUST_BEGIN_IN_TOPIC:
                message = new Message("Veza mora zapoceti u pojmu", "ERROR", timestamp); break;
            case ASSOCIATION_MUST_END_IN_TOPIC:
                message = new Message("Veza se mora zavrsiti u pojmu", "ERROR", timestamp); break;
            case ASSOCIATION_ALREADY_EXISTS:
                message = new Message("Veza vec postoji", "ERROR", timestamp); break;
            case ASSOCIATION_INTO_ITSELF:
                message = new Message("Pojam ne moze imati vezu ka sebi", "ERROR", timestamp); break;
        }

        notifySubscribers(message);
    }
}