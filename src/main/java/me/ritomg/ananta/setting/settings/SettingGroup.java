package me.ritomg.ananta.setting.settings;

import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.Setting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SettingGroup extends Setting {


    public SettingGroup(String name, Module parent, String description, Supplier<Boolean> isVisible) {
        super(name, parent, description, isVisible);
    }


    public static final class SettingGroupBuilder {
        private String name;
        private Module parent;
        private Supplier<Boolean> isVisible = ()->true;
        private String description;
        private List<Setting> subSettings = new ArrayList<>();

        public SettingGroupBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SettingGroupBuilder withParent(Module parent) {
            this.parent = parent;
            return this;
        }

        public SettingGroupBuilder withIsVisible(Supplier<Boolean> isVisible) {
            this.isVisible = isVisible;
            return this;
        }

        public SettingGroupBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public SettingGroupBuilder withSubSettings(List<Setting> subSettings) {
            this.subSettings = subSettings;
            return this;
        }

        public SettingGroup build() {
            SettingGroup settingGroup = new SettingGroup(name, parent, description, isVisible);
            settingGroup.setSubSettings(subSettings);
            parent.addSetting(settingGroup);
            return settingGroup;
        }
    }
}
