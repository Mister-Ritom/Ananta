package me.ritomg.ananta.setting.settings;

import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.setting.Setting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class NumberSetting extends Setting {

    private int current;
    private int min;
    private int max;

    public NumberSetting(String name, Module parent, int current, int min, int max) {
        super(name, parent);
        this.current = current;
        this.min = min;
        this.max = max;
    }

    public NumberSetting(String name, Module parent, Supplier<Boolean> isVisible, int current, int min, int max) {
        super(name, parent, isVisible);
        this.current = current;
        this.min = min;
        this.max = max;
    }

    public NumberSetting(String name, Module parent, String desc, Supplier<Boolean> isVisible, int current, int min, int max) {
        super(name, parent, desc, isVisible);
        this.current = current;
        this.min = min;
        this.max = max;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
        for (Module m : ModuleManager.getModules()) {
            if (m.isEnabled()) {
                m.onSettingChange(getParent(),this);
            }
        }
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

    public static final class NumberSettingBuilder {
        private String name;
        private Module parent;
        private Supplier<Boolean> isVisible = ()->true;
        private String description;
        private List<Setting> subSettings = new ArrayList<>();
        private int current;
        private int min;
        private int max;

        public NumberSettingBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public NumberSettingBuilder withParent(Module parent) {
            this.parent = parent;
            return this;
        }

        public NumberSettingBuilder withIsVisible(Supplier<Boolean> isVisible) {
            this.isVisible = isVisible;
            return this;
        }

        public NumberSettingBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public NumberSettingBuilder withSubSettings(List<Setting> subSettings) {
            this.subSettings = subSettings;
            return this;
        }

        public NumberSettingBuilder withCurrent(int current) {
            this.current = current;
            return this;
        }

        public NumberSettingBuilder withMin(int min) {
            this.min = min;
            return this;
        }

        public NumberSettingBuilder withMax(int max) {
            this.max = max;
            return this;
        }

        public NumberSetting build() {
            NumberSetting numberSetting = new NumberSetting(name, parent, current, min, max);
            numberSetting.setIsVisible(isVisible);
            numberSetting.setDescription(description);
            numberSetting.setSubSettings(subSettings);
            parent.addSetting(numberSetting);
            return numberSetting;
        }

        public NumberSetting build(boolean subSetting) {
            NumberSetting numberSetting = new NumberSetting(name, parent, current, min, max);
            numberSetting.setIsVisible(isVisible);
            numberSetting.setDescription(description);
            numberSetting.setSubSettings(subSettings);
            if (!subSetting)
                parent.addSetting(numberSetting);
            return numberSetting;
        }
    }
}
