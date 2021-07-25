package me.ritomg.ananta.setting;

import me.ritomg.ananta.module.Module;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Setting {

    private String name;
    private final Module parent;
    private Supplier<Boolean> isVisible;
    private String description;
    private List<Setting> subSettings = new ArrayList<>();

    public Setting(String name,Module parent) {
        this.name = name;
        this.parent = parent;
        this.isVisible = ()->true;
        description = "no description";
    }

    public Setting(String name,Module parent, Supplier<Boolean> isVisible) {
        this.name = name;
        this.parent = parent;
        this.isVisible = isVisible;
        description = "no description";
    }

    public Setting(String name,Module parent,String description, Supplier<Boolean> isVisible) {
        this.name = name;
        this.parent = parent;
        this.isVisible = isVisible;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Module getParent() {
        return parent;
    }


    public boolean getIsVisible() {
        return isVisible.get();
    }

    public void setIsVisible(Supplier<Boolean> isVisible) {
        this.isVisible = isVisible;
    }
    public Setting addSubSetting (Setting setting) {
        subSettings.add(setting);
        return setting;
    }

    public Setting getSubsettingbyname(String name) {
        return subSettings.stream().filter(setting -> setting.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public List<Setting> getSubSettings() {
        return subSettings;
    }

    public void setSubSettings(List<Setting> subSettings) {
        this.subSettings = subSettings;
    }
}
