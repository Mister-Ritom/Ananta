package me.ritomg.ananta.module;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Module {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    private @interface Info {
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

    public void toggle() {
        if (isEnabled)
            disable();
        else enable();
    }

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

    public void onEnable() {}
    public void onDisable() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
