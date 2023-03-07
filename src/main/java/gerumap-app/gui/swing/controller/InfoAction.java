package dsw.gerumap.app.gui.swing.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class InfoAction extends AbstractRudokAction{

    public InfoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/info.png"));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //OTVARA PROZOR SA IMENOM PREZIMENOM BR INDEXA STUDENTA

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        panel.add(new JLabel("Luka Labalo 82/22RN (205)"));
        panel.add(new JLabel("Irina Tomic 72/22RN (205)"));

        JDialog dialog = new JDialog();
        dialog.add(panel);
        dialog.setSize(200, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
