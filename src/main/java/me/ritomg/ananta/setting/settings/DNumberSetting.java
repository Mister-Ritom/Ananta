package me.ritomg.ananta.setting.settings;

import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.setting.Setting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DNumberSetting extends Setting {

    private double current;
    private double max;
    private double min;

    public DNumberSetting(String name, Module parent, double current, double min, double max) {
        super(name, parent);
        this.current = current;
        this.min = min;
        this.max = max;
    }

    public DNumberSetting(String name, Module parent, Supplier<Boolean> isVisible, double current, double min, double max) {
        super(name, parent, isVisible);
        this.current = current;
        this.min = min;
        this.max = max;
    }

    public DNumberSetting(String name, Module parent, String description, Supplier<Boolean> isVisible, double current, double min, double max) {
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
        for (Module m : ModuleManager.getModules()) {
            if (m.isEnabled()) {
                m.onSettingChange(getParent(),this);
            }
        }
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

    public static final class DNumberSettingBuilder {
        private String name;
        private Module parent;
        private Supplier<Boolean> isVisible = ()->true;
        private String description;
        private List<Setting> subSettings = new ArrayList<>();
        private double current;
        private double max;
        private double min;

        public DNumberSettingBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DNumberSettingBuilder withParent(Module parent) {
            this.parent = parent;
            return this;
        }

        public DNumberSettingBuilder withIsVisible(Supplier<Boolean> isVisible) {
            this.isVisible = isVisible;
            return this;
        }

        public DNumberSettingBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public DNumberSettingBuilder withSubSettings(List<Setting> subSettings) {
            this.subSettings = subSettings;
            return this;
        }

        public DNumberSettingBuilder withCurrent(double current) {
            this.current = current;
            return this;
        }

        public DNumberSettingBuilder withMax(double max) {
            this.max = max;
            return this;
        }

        public DNumberSettingBuilder withMin(double min) {
            this.min = min;
            return this;
        }

        public DNumberSetting build() {
            DNumberSetting dNumberSetting = new DNumberSetting(name, parent, current, min, max);
            dNumberSetting.setIsVisible(isVisible);
            dNumberSetting.setDescription(description);
            dNumberSetting.setSubSettings(subSettings);
            parent.addSetting(dNumberSetting);
            return dNumberSetting;
        }

        public DNumberSetting build(boolean subSetting) {
            DNumberSetting dNumberSetting = new DNumberSetting(name, parent, current, min, max);
            dNumberSetting.setIsVisible(isVisible);
            dNumberSetting.setDescription(description);
            dNumberSetting.setSubSettings(subSettings);
            if (!subSetting)
                parent.addSetting(dNumberSetting);
            return dNumberSetting;
        }

    }
}
