package me.ritomg.ananta.setting.settings;

import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.Setting;

import java.util.List;

public class ModeSetting extends Setting {

    private List<String> modes;
    private String currentMode;

    public void increaseMode() {
        int modeindex = modes.indexOf(currentMode);
        modeindex = (modeindex+1)%modes.size();
        setCurrentMode(modes.get(modeindex));
    }

    public ModeSetting(String name, Module parent, List<String> modes, String currentMode) {
        super(name, parent);
        this.modes = modes;
        this.currentMode = currentMode;
    }

    public ModeSetting(String name, Module parent, boolean isVisible, List<String> modes, String currentMode) {
        super(name, parent, isVisible);
        this.modes = modes;
        this.currentMode = currentMode;
    }

    public ModeSetting(String name, Module parent,String description, boolean isVisible, List<String> modes, String currentMode) {
        super(name, parent,description, isVisible);
        this.modes = modes;
        this.currentMode = currentMode;
    }

    public List<String> getModes() {
        return modes;
    }

    public boolean is(String s) {
        return getCurrentMode().toLowerCase().equalsIgnoreCase(s.toLowerCase());
    }

    public void setModes(List<String> modes) {
        this.modes = modes;
    }

    public String getCurrentMode() {
        return currentMode;
    }

    public void setCurrentMode(String currentMode) {
        this.currentMode = currentMode;
    }
}
