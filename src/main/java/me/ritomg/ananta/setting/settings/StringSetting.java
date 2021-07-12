package me.ritomg.ananta.setting.settings;

import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.Setting;

public class StringSetting extends Setting {

    private String text;

    public StringSetting(String name, Module parent, String text) {
        super(name, parent);
        this.text = text;
    }

    public StringSetting(String name, Module parent, boolean isVisible, String text) {
        super(name, parent, isVisible);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
