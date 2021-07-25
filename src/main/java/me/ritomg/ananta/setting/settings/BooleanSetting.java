package me.ritomg.ananta.setting.settings;

import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.setting.Setting;

import java.util.ArrayList;
import java.util.List;
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

    public static final class BooleanSettingBuilder {
        private String name;
        private Module parent;
        private Supplier<Boolean> isVisible = ()->true;
        private String description;
        private List<Setting> subSettings = new ArrayList<>();
        private boolean isOn;

        public BooleanSettingBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public BooleanSettingBuilder withParent(Module parent) {
            this.parent = parent;
            return this;
        }

        public BooleanSettingBuilder withIsVisible(Supplier<Boolean> isVisible) {
            this.isVisible = isVisible;
            return this;
        }

        public BooleanSettingBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public BooleanSettingBuilder withSubSettings(List<Setting> subSettings) {
            this.subSettings = subSettings;
            return this;
        }

        public BooleanSettingBuilder withIsOn(boolean isOn) {
            this.isOn = isOn;
            return this;
        }

        public BooleanSetting build() {
            BooleanSetting booleanSetting = new BooleanSetting(name, parent, isOn);
            booleanSetting.setIsVisible(isVisible);
            booleanSetting.setDescription(description);
            booleanSetting.setSubSettings(this.subSettings);
            parent.addSetting(booleanSetting);
            return booleanSetting;
        }

        public BooleanSetting build(boolean subSetting) {
            BooleanSetting booleanSetting = new BooleanSetting(name, parent, isOn);
            booleanSetting.setIsVisible(isVisible);
            booleanSetting.setDescription(description);
            booleanSetting.setSubSettings(this.subSettings);
            if (!subSetting) {
                parent.addSetting(booleanSetting);
            }
            return booleanSetting;
        }

    }
}
