package me.ritomg.ananta.setting.settings;

import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.setting.Setting;

import java.util.List;
import java.util.function.Supplier;

public class ModeSetting extends Setting {

    private final List<String> modes;
    private String currentMode;

    public void increaseMode() {
        int modeindex = modes.indexOf(currentMode);
        modeindex = (modeindex+1)%modes.size();
        setCurrentMode(modes.get(modeindex));
    }

    public void decreaseMode() {
        int modeindex = modes.indexOf(currentMode);
        modeindex =-1;
        if (modeindex <0) modeindex = modes.size()-1;
        setCurrentMode(modes.get(modeindex));
    }

    public ModeSetting(String name, Module parent, List<String> modes, String currentMode) {
        super(name, parent);
        this.modes = modes;
        this.currentMode = currentMode;
    }

    public ModeSetting(String name, Module parent, Supplier<Boolean> isVisible, List<String> modes, String currentMode) {
        super(name, parent, isVisible);
        this.modes = modes;
        this.currentMode = currentMode;
    }

    public ModeSetting(String name, Module parent,String description, Supplier<Boolean> isVisible, List<String> modes, String currentMode) {
        super(name, parent,description, isVisible);
        this.modes = modes;
        this.currentMode = currentMode;
    }

    public List<String> getModes() {
        return modes;
    }

    public boolean is(String s) {
        return getCurrentMode().equalsIgnoreCase(s);
    }


    public String getCurrentMode() {
        return currentMode;
    }

    public void setCurrentMode(String currentMode) {
        this.currentMode = currentMode;
        for (Module m : ModuleManager.getModules()) {
            if (m.isEnabled()) {
                m.onSettingChange(getParent(), this);
            }
        }
    }
}
