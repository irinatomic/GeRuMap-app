package dsw.gerumap.app.gui.swing;

import dsw.gerumap.app.gui.swing.views.MainFrame;
import dsw.gerumap.app.interfaces.Gui;
import dsw.gerumap.app.messages.Message;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class SwingGui implements Gui {

    private MainFrame instance;

    public SwingGui(){ }

    @Override
    public void start() {
        instance = MainFrame.getInstance();
        disableRedoAction();
        disableUndoAction();
        instance.setVisible(true);
    }

    @Override
    public void update(Object notification) {
        ImageIcon sadPanda = new ImageIcon(getClass().getResource("/images/sadpanda.jpg"));
        JFrame errorFrame = new JFrame();           //zbog sad pande
        errorFrame.setAlwaysOnTop(true);
        JOptionPane.showMessageDialog(errorFrame, ((Message)notification).getText(), "Oops", 0, sadPanda);
    }

    @Override
    public void enableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
    }

    @Override
    public void disableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
    }

    @Override
    public void disableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
    }
    @Override
    public void enableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
    }
}
