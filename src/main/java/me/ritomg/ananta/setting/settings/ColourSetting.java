package me.ritomg.ananta.setting.settings;

import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.setting.Setting;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ColourSetting extends Setting  {

    private Color color;
    private  boolean isRainbow = false;
    private final boolean allowRainbow;
    private final boolean hasAlpha;

    public ColourSetting(String name, Module parent, Color color, boolean isRainbow, boolean allowRainbow) {
        super(name, parent);
        this.color = color;
        this.isRainbow = isRainbow;
        this.allowRainbow = allowRainbow;
        hasAlpha = false;
    }

    public ColourSetting(String name, Module parent, Supplier<Boolean> isVisible, Color color, boolean isRainbow, boolean allowRainbow, boolean hasAlpha) {
        super(name, parent, isVisible);
        this.color = color;
        this.isRainbow = isRainbow;
        this.allowRainbow = allowRainbow;
        this.hasAlpha = hasAlpha;
    }

    public ColourSetting(String name, Module parent, Color color) {
        super(name, parent);
        this.color = color;
        isRainbow = false;
        allowRainbow = true;
        this.hasAlpha = false;
    }

    public ColourSetting(String name, Module parent,Supplier<Boolean> isVisible, Color color) {
        super(name, parent, isVisible);
        this.color = color;
        this.isRainbow = false;
        this.allowRainbow = true;
        hasAlpha = false;
    }


    public ColourSetting(String name, Module parent, Supplier<Boolean> isVisible, Color color,boolean hasAlpha) {
        super(name, parent, isVisible);
        this.color = color;
        this.isRainbow = false;
        this.allowRainbow = true;
        this.hasAlpha = hasAlpha;
    }

    public ColourSetting(String name, Module parent,String description, Supplier<Boolean> isVisible, Color color,boolean hasAlpha) {
        super(name, parent,description, isVisible);
        this.color = color;
        this.isRainbow = false;
        this.allowRainbow = true;
        this.hasAlpha = hasAlpha;
    }

    public ColourSetting(String name, Module parent,String description, Supplier<Boolean> isVisible, Color color,boolean hasAlpha,boolean allowRainbow,boolean isRainbow) {
        super(name, parent,description, isVisible);
        this.color = color;
        this.isRainbow = isRainbow;
        this.allowRainbow = allowRainbow;
        this.hasAlpha = hasAlpha;
    }

    public long getColorRGB() {
        return getColor().getRGB() & 0xFFFFFF;
    }

    public void setColorRGB(long RGB) {
        setColor(new Color((int) (RGB & 0xFFFFFF)));
    }

    public Color getColor() {
        float hue = System.currentTimeMillis() % (360 * 32) / (360f * 32);
        if (isRainbow) return fromHSB(hue,1,color.getAlpha());
       else return this.color;
    }

    public static Color fromHSB(float hue, float saturation, float brightness) {
        return Color.getHSBColor(hue, saturation, brightness);
    }

    public static Color getColor(Color color, int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    public void setColor(Color color) {
        this.color = color;
        for (Module m : ModuleManager.getModules()) {
            if (m.isEnabled()) {
                m.onSettingChange(getParent(), this);
            }
        }
    }

    public boolean isRainbow() {
        return isRainbow;
    }

    public void setRainbow(boolean rainbow) {
        isRainbow = rainbow;
    }

    public boolean isAllowRainbow() {
        return allowRainbow;
    }

    public boolean isHasAlpha() {
        return hasAlpha;
    }

    public static final class ColourSettingBuilder {
        private String name;
        private Module parent;
        private Supplier<Boolean> isVisible = ()->true;
        private String description;
        private java.util.List<Setting> subSettings = new ArrayList<>();
        private Color color;
        private  boolean isRainbow = false;
        private boolean allowRainbow = true;
        private boolean hasAlpha = false;

        public ColourSettingBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ColourSettingBuilder withParent(Module parent) {
            this.parent = parent;
            return this;
        }

        public ColourSettingBuilder withIsVisible(Supplier<Boolean> isVisible) {
            this.isVisible = isVisible;
            return this;
        }

        public ColourSettingBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ColourSettingBuilder withSubSettings(List<Setting> subSettings) {
            this.subSettings = subSettings;
            return this;
        }

        public ColourSettingBuilder withColor(Color color) {
            this.color = color;
            return this;
        }

        public ColourSettingBuilder withIsRainbow(boolean isRainbow) {
            this.isRainbow = isRainbow;
            return this;
        }

        public ColourSettingBuilder withAllowRainbow(boolean allowRainbow) {
            this.allowRainbow = allowRainbow;
            return this;
        }

        public ColourSettingBuilder withHasAlpha(boolean hasAlpha) {
            this.hasAlpha = hasAlpha;
            return this;
        }

        public ColourSetting build() {
            ColourSetting colourSetting = new ColourSetting(name, parent, isVisible, color, isRainbow, allowRainbow, hasAlpha);
            colourSetting.setDescription(description);
            colourSetting.setSubSettings(subSettings);
            parent.addSetting(colourSetting);
            return colourSetting;
        }

        public ColourSetting build(boolean subSetting) {
            ColourSetting colourSetting = new ColourSetting(name, parent, isVisible, color, isRainbow, allowRainbow, hasAlpha);
            colourSetting.setDescription(description);
            colourSetting.setSubSettings(subSettings);
            if (!subSetting)
                parent.addSetting(colourSetting);
            return colourSetting;
        }

    }
}
