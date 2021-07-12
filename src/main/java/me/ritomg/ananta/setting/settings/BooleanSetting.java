package me.ritomg.ananta.setting.settings;

import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.Setting;

public class BooleanSetting extends Setting {

    private boolean isOn;

    public BooleanSetting(String name, Module parent, boolean isOn) {
        super(name, parent);
        this.isOn = isOn;
    }

    public BooleanSetting(String name, Module parent, boolean isVisible,boolean isOn) {
        super(name, parent, isVisible);
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public void On(boolean on) {
        isOn = on;
    }
}
