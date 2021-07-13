package me.ritomg.ananta.setting.settings;

import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.Setting;

public class DNumberSetting extends Setting {

    private double current,min,max;

    public DNumberSetting(String name, Module parent, double current, double min, double max) {
        super(name, parent);
        this.current = current;
        this.min = min;
        this.max = max;
    }

    public DNumberSetting(String name, Module parent, boolean isVisible, double current, double min, double max) {
        super(name, parent, isVisible);
        this.current = current;
        this.min = min;
        this.max = max;
    }

    public DNumberSetting(String name, Module parent, String description, boolean isVisible, double current, double min, double max) {
        super(name, parent,description, isVisible);
        this.current = current;
        this.min = min;
        this.max = max;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
    
}
