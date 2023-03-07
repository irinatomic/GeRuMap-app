package dsw.gerumap.app.gui.swing.controller;

import lombok.Getter;
import lombok.Setter;

//SADRZI JEDAN PRIMERAK SVAKE NABROJANE AKCIJE, ZARAD INICIJALIZACIJE AKCIJA
@Getter
@Setter
public class ActionManager {

    private ExitAction exitAction;
    private NewAction newAction;
    private RenameAction renameAction;
    private InfoAction infoAction;
    private RemoveAction removeAction;
    private AddAuthorAction addAuthorAction;
    private DeleteAction deleteAction;
    private NewAssociationAction newAssociationAction;
    private NewTopicAction newTopicAction;
    private SelectAction selectAction;
    private MoveAction moveAction;
    private ZoomInAction zoomInAction;
    private ZoomOutAction zoomOutAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private ModifyAction modifyAction;
    private EditSelectedColorInsideAction editSelectedColorInsideAction;
    private EditSelectedColorOutsideAction editSelectedColorOutsideAction;
    private EditSelectedNameAction editSelectedNameAction;
    private OpenProjectAction openProjectAction;
    private SaveProjectAction saveProjectAction;
    private PictureAction pictureAction;
    private CentralTopicAction centralTopicAction;
    private SaveTemplateAction saveTemplateAction;
    private OpenTemplateAction openTemplateAction;

    public ActionManager(){ initialiseActions();}

    private void initialiseActions(){
        exitAction = new ExitAction();
        newAction = new NewAction();
        renameAction = new RenameAction();
        infoAction = new InfoAction();
        removeAction = new RemoveAction();
        addAuthorAction = new AddAuthorAction();
        newTopicAction = new NewTopicAction();
        newAssociationAction = new NewAssociationAction();
        deleteAction = new DeleteAction();
        selectAction = new SelectAction();
        moveAction = new MoveAction();
        zoomInAction = new ZoomInAction();
        zoomOutAction = new ZoomOutAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        modifyAction = new ModifyAction();
        editSelectedNameAction = new EditSelectedNameAction();
        editSelectedColorOutsideAction = new EditSelectedColorOutsideAction();
        editSelectedColorInsideAction = new EditSelectedColorInsideAction();
        openProjectAction = new OpenProjectAction();
        saveProjectAction = new SaveProjectAction();
        pictureAction = new PictureAction();
        centralTopicAction = new CentralTopicAction();
        saveTemplateAction = new SaveTemplateAction();
        openTemplateAction = new OpenTemplateAction();
    }
}