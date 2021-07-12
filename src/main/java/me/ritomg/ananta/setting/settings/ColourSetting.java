package me.ritomg.ananta.setting.settings;

import com.lukflug.panelstudio.setting.IColorSetting;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.Setting;

import java.awt.*;

public class ColourSetting extends Setting  {

    private final Color color;
    private final boolean isRainbow,allowRainbow;

    public ColourSetting(String name, Module parent, Color color, boolean isRainbow, boolean allowRainbow) {
        super(name, parent);
        this.color = color;
        this.isRainbow = isRainbow;
        this.allowRainbow = allowRainbow;
    }

    public ColourSetting(String name, Module parent, boolean isVisible, Color color, boolean isRainbow, boolean allowRainbow) {
        super(name, parent, isVisible);
        this.color = color;
        this.isRainbow = isRainbow;
        this.allowRainbow = allowRainbow;
    }

    public ColourSetting(String name, Module parent, Color color) {
        super(name, parent);
        this.color = color;
        isRainbow = false;
        allowRainbow = true;
    }

    public ColourSetting(String name, Module parent, boolean isVisible, Color color) {
        super(name, parent, isVisible);
        this.color = color;
        this.isRainbow = false;
        this.allowRainbow = true;
    }


}
