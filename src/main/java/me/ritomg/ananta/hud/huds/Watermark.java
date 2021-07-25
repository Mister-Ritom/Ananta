package me.ritomg.ananta.hud.huds;

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

import java.awt.*;

@Module.Info(name = "Watermark", description = "Shows watermark on screen", category = Category.Client)
public class Watermark extends Hud {

    ColourSetting color  = new ColourSetting.ColourSettingBuilder()
            .withParent(this)
            .withName("Color")
            .withDescription("The color text")
            .withColor(new Color(255,0,255))
            .build();

    public Watermark() {
        super(20,25);
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