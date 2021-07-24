package me.ritomg.ananta.setting.settings;

import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.setting.Setting;

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
}
