package me.ritomg.ananta.module;

import me.ritomg.ananta.setting.Setting;
import me.ritomg.ananta.setting.settings.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Module {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Info {
        String name();
        String description();
        Category category();
        int bind() default Keyboard.KEY_NONE;
        boolean isEnabled() default false;
        boolean isAlwaysEnabled() default false;
    }

    public Info getInfo() {
        return getClass().getAnnotation(Info.class);
    }

    private String name = getInfo().name();
    private String description = getInfo().description();
    private int bind = getInfo().bind();
    private Category category = getInfo().category();
    private boolean isEnabled = getInfo().isEnabled();
    private boolean isAlwaysEnabled = getInfo().isAlwaysEnabled();
    private List<Setting> settings = new ArrayList<>();

    public void disable() {
        isEnabled = false;
        MinecraftForge.EVENT_BUS.unregister(this);
        onDisable();
    }

    public void enable() {
        isEnabled = true;
        MinecraftForge.EVENT_BUS.register(this);
        onEnable();
    }

    public void toggle() {
        if (isEnabled)
            disable();
        else if (!isEnabled)
            enable();
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onUpdate() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Setting getSettingByName(String name) {
        return settings.stream().filter(setting -> setting.getName().toLowerCase().equalsIgnoreCase(name.toLowerCase())).findFirst().orElse(null);
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBind() {
        return bind;
    }

    public void setBind(int bind) {
        this.bind = bind;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isAlwaysEnabled() {
        return isAlwaysEnabled;
    }

    public void setAlwaysEnabled(boolean alwaysEnabled) {
        isAlwaysEnabled = alwaysEnabled;
    }

    public void addSetting(Setting setting) {
        settings.add(setting);
    }

    public BooleanSetting addBooleanSetting(String name, boolean value) {
        BooleanSetting setting = new BooleanSetting(name,this,value);
        addSetting(setting);
        return setting;
    }

    public StringSetting addStringSetting(String name, String value) {
        StringSetting setting = new StringSetting(name,this,value);
        addSetting(setting);
        return setting;
    }

    public NumberSetting addIntegerSetting(String name,int min,int value,int max) {
        NumberSetting setting = new NumberSetting(name,this,value,min,max);
        addSetting(setting);
        return setting;
    }

    public DNumberSetting addDoublesetting(String name,double min,double value,double max) {
        DNumberSetting setting = new DNumberSetting(name,this,value,min,max);
        addSetting(setting);
        return setting;
    }

    public ModeSetting addModeSetting(String name,String value,List<String> modes) {
        ModeSetting setting = new ModeSetting(name,this,modes,value);
        addSetting(setting);
        return setting;
    }

    public ColourSetting addColorSetting(String name, Color value) {
        ColourSetting setting = new ColourSetting(name,this,value);
        addSetting(setting);
        return setting;
    }

}
