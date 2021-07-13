package me.ritomg.ananta.setting;

import me.ritomg.ananta.module.Module;

import java.util.function.Supplier;

public class Setting {

    private String name;
    private Module parent;
    private Supplier<Boolean> isVisible;
    private String description;

    public Setting(String name,Module parent) {
        this.name = name;
        this.parent = parent;
        this.isVisible = ()->true;
        description = "no description";
    }

    public Setting(String name,Module parent, boolean isVisible) {
        this.name = name;
        this.parent = parent;
        this.isVisible = ()->isVisible;
        description = "no description";
    }

    public Setting(String name,Module parent,String description, boolean isVisible) {
        this.name = name;
        this.parent = parent;
        this.isVisible = ()->isVisible;
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

    public void setParent(Module parent) {
        this.parent = parent;
    }

    public boolean getIsVisible() {
        return isVisible.get();
    }

    public void setIsVisible(Supplier<Boolean> isVisible) {
        this.isVisible = isVisible;
    }
}
