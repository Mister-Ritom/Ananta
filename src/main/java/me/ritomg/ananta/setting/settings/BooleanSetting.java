package me.ritomg.ananta.setting.settings;

import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.setting.Setting;

import java.util.function.Supplier;

public class BooleanSetting extends Setting {

    private boolean isOn;

    public BooleanSetting(String name, Module parent, boolean isOn) {
        super(name, parent);
        this.isOn = isOn;
    }

    public BooleanSetting(String name, Module parent, Supplier<Boolean> isVisible, boolean isOn) {
        super(name, parent, isVisible);
        this.isOn = isOn;
    }

    public BooleanSetting(String name, Module parent, String description,Supplier<Boolean> isVisible,boolean isOn) {
        super(name,parent,description,isVisible);
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public void On(boolean on) {
        isOn = on;
        for (Module m : ModuleManager.getModules()) {
            if (m.isEnabled()) {
                m.onSettingChange(getParent(), this);
            }
        }
    }
}
