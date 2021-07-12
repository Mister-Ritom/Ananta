package me.ritomg.ananta.setting.settings;

import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.Setting;

public class NumberSetting extends Setting {

    private int current,min,max;

    public NumberSetting(String name, Module parent, int current, int min, int max) {
        super(name, parent);
        this.current = current;
        this.min = min;
        this.max = max;
    }

    public NumberSetting(String name, Module parent, boolean isVisible, int current, int min, int max) {
        super(name, parent, isVisible);
        this.current = current;
        this.min = min;
        this.max = max;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
