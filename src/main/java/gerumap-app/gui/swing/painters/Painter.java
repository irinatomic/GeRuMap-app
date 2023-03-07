package dsw.gerumap.app.gui.swing.painters;

import dsw.gerumap.app.maprepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public abstract class Painter {

    private Element element;
    private Shape shape;
    private Boolean selected;

    private boolean checked = false;

    private int graflevel = 0;

    protected Painter(Element element) {
        this.element = element;
        this.selected = false;
    }

    public abstract void draw(Graphics g);
    public abstract boolean elementAt(int x, int y);

    public void setSelected(Boolean selected) {
        this.selected = selected;
        element.setSelected(selected);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
