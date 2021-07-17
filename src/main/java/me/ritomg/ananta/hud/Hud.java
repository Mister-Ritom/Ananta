package me.ritomg.ananta.hud;

import com.lukflug.panelstudio.component.IFixedComponent;
import com.lukflug.panelstudio.theme.ITheme;
import me.ritomg.ananta.Ananta;
import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;

import java.awt.*;

//code looks skidded ha!
public abstract class Hud extends Module {

    public int x,y;

    public Hud( int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static final int LIST_BORDER=1;
    protected IFixedComponent component;
    public Point position = new Point(x, y);

    public abstract void populate(ITheme theme);

    public IFixedComponent getComponent() {
        return component;
    }

    public void resetPosition() {
        component.setPosition(Ananta.INSTANCE.hudGui.getInterface(), position);
    }

}
