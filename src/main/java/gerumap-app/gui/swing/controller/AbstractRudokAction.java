package dsw.gerumap.app.gui.swing.controller;

import javax.swing.*;
import java.net.URL;

//Apstraktna natklasa svim akcijama
public abstract class AbstractRudokAction extends AbstractAction {

    public Icon loadIcon(String fileName){

        URL imageURL = getClass().getResource(fileName);
        Icon icon = null;

        if(imageURL != null) icon = new ImageIcon(imageURL);
        else System.err.println("Resource not found" + fileName);

        return icon;
    }

}
