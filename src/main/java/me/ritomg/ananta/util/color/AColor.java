package me.ritomg.ananta.util.color;

import me.ritomg.ananta.setting.settings.ColourSetting;

import java.awt.*;
import java.awt.color.ColorSpace;

//TOOD work on it more
public class AColor extends Color {
    public AColor(int r, int g, int b) {
        super(r, g, b);
    }

    public AColor(int r, int g, int b, int a) {
        super(r, g, b, a);
    }

    public AColor(int rgb) {
        super(rgb);
    }

    public AColor(int rgba, boolean hasalpha) {
        super(rgba, hasalpha);
    }

    public AColor(float r, float g, float b) {
        super(r, g, b);
    }

    public AColor(float r, float g, float b, float a) {
        super(r, g, b, a);
    }

    public AColor(Color color) {
        super(color.getRGB());
    }

    public AColor(Color color, int alpha) {
        super(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }


}
