package me.ritomg.ananta.setting.settings;

import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.Setting;

import java.awt.*;

public class ColourSetting extends Setting  {

    private Color color;
    private  boolean isRainbow = false,allowRainbow;
    private  boolean hasAlpha;

    public ColourSetting(String name, Module parent, Color color, boolean isRainbow, boolean allowRainbow) {
        super(name, parent);
        this.color = color;
        this.isRainbow = isRainbow;
        this.allowRainbow = allowRainbow;
        hasAlpha = false;
    }

    public ColourSetting(String name, Module parent, boolean isVisible, Color color, boolean isRainbow, boolean allowRainbow,boolean hasAlpha) {
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

    public ColourSetting(String name, Module parent, boolean isVisible, Color color) {
        super(name, parent, isVisible);
        this.color = color;
        this.isRainbow = false;
        this.allowRainbow = true;
        hasAlpha = false;
    }


    public ColourSetting(String name, Module parent, boolean isVisible, Color color,boolean hasAlpha) {
        super(name, parent, isVisible);
        this.color = color;
        this.isRainbow = false;
        this.allowRainbow = true;
        this.hasAlpha = hasAlpha;
    }

    public ColourSetting(String name, Module parent,String description, boolean isVisible, Color color,boolean hasAlpha) {
        super(name, parent,description, isVisible);
        this.color = color;
        this.isRainbow = false;
        this.allowRainbow = true;
        this.hasAlpha = hasAlpha;
    }

    public ColourSetting(String name, Module parent,String description, boolean isVisible, Color color,boolean hasAlpha,boolean allowRainbow,boolean isRainbow) {
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
}
