package me.ritomg.ananta.hud.huds;

import java.awt.Color;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.setting.Labeled;
import com.lukflug.panelstudio.theme.ITheme;
import me.ritomg.ananta.Ananta;
import me.ritomg.ananta.hud.Hud;
import me.ritomg.ananta.hud.HudGui;
import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.settings.ColourSetting;

@Module.Info(name = "Watermark", description = "Shows watermark on sceen", category = Category.Client)
public class Watermark extends Hud {

    ColourSetting color  = addColorSetting("Color", new Color(0,255,0));

    public Watermark() {
        super(5,5);
    }

    @Override
    public void populate(ITheme theme) {
    	component = new ListComponent(new Labeled(getName(),null,()->true), position, getName(), new WatermarkList(), HudGui.FONT_HEIGHT, Hud.LIST_BORDER);
    }

    private class WatermarkList implements HUDList {

        @Override
        public int getSize() {
            return 1;
        }

        @Override
        public String getItem(int index) {
            return "Ananta " + Ananta.VERSION;
        }

        @Override
        public Color getItemColor(int index) {
            return color.getColor();
        }

        @Override
        public boolean sortUp() {
            return false;
        }

        @Override
        public boolean sortRight() {
            return false;
        }
    }
}