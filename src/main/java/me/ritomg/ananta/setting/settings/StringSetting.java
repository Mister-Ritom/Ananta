package me.ritomg.ananta.setting.settings;

import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.setting.Setting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class StringSetting extends Setting {

    private String text;

    public StringSetting(String name, Module parent, String text) {
        super(name, parent);
        this.text = text;
    }

    public StringSetting(String name, Module parent, Supplier<Boolean> isVisible, String text) {
        super(name, parent, isVisible);
        this.text = text;
    }

    public StringSetting(String name, Module parent, String desciption, Supplier<Boolean> isVisible, String text) {
        super(name, parent,desciption, isVisible);
        this.text = text;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        for (Module m : ModuleManager.getModules()) {
            if (m.isEnabled()) {
                m.onSettingChange(getParent(),this);
            }
        }
    }

    public static final class StringSettingBuilder {
        private String name;
        private Module parent;
        private Supplier<Boolean> isVisible = ()->true;
        private String description;
        private List<Setting> subSettings = new ArrayList<>();
        private String text;

        public StringSettingBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public StringSettingBuilder withParent(Module parent) {
            this.parent = parent;
            return this;
        }

        public StringSettingBuilder withIsVisible(Supplier<Boolean> isVisible) {
            this.isVisible = isVisible;
            return this;
        }

        public StringSettingBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public StringSettingBuilder withSubSettings(List<Setting> subSettings) {
            this.subSettings = subSettings;
            return this;
        }

        public StringSettingBuilder withText(String text) {
            this.text = text;
            return this;
        }

        public StringSetting build() {
            StringSetting stringSetting = new StringSetting(name, parent, text);
            stringSetting.setIsVisible(isVisible);
            stringSetting.setDescription(description);
            stringSetting.setSubSettings(subSettings);
            parent.addSetting(stringSetting);
            return stringSetting;
        }

        public StringSetting build(boolean subSetting) {
            StringSetting stringSetting = new StringSetting(name, parent, text);
            stringSetting.setIsVisible(isVisible);
            stringSetting.setDescription(description);
            stringSetting.setSubSettings(subSettings);
            if (!subSetting)
                parent.addSetting(stringSetting);
            return stringSetting;
        }

    }
}
