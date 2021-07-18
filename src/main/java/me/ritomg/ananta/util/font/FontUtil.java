package me.ritomg.ananta.util.font;

import me.ritomg.ananta.Ananta;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.module.modules.client.CustomFont;
import me.ritomg.ananta.util.Util;
import net.minecraft.client.Minecraft;

import java.awt.*;

@SuppressWarnings("unused")
public class FontUtil extends Util {

    public static void drawStringWithShadow(boolean customFont, String text, int x, int y, Color color) {
        if (customFont) {
            Ananta.customFont.drawStringWithShadow(text, x, y, color);
        } else {
            mc.fontRenderer.drawStringWithShadow(text, x, y, color.getRGB());
        }
    }

    public static void drawString(String text, int x, int y, Color color) {
        drawString(ModuleManager.getModule(CustomFont.class).isEnabled(), text, x, y, color);
    }

    public static void drawString(boolean customFont, String text, int x, int y, Color color) {
        if (customFont) {
            Ananta.customFont.drawString(text,x,y,color);
        }
        else mc.fontRenderer.drawString(text,x,y,color.getRGB());
    }

    public static void drawStringWithShadow(String text, int x, int y, Color color) {
        drawStringWithShadow(ModuleManager.getModule(CustomFont.class).isEnabled(), text, x, y, color);
    }

    public static int getStringWidth(boolean customFont, String string) {
        if (customFont) {
            return Ananta.customFont.getStringWidth(string);
        } else {
            return mc.fontRenderer.getStringWidth(string);
        }
    }

    public static int getStringWidth(String s) {
        return getStringWidth(ModuleManager.getModule(CustomFont.class).isEnabled(),s);
    }

    public static int getFontHeight(boolean customFont) {
        if (customFont) {
            return Ananta.customFont.getHeight();
        } else {
            return mc.fontRenderer.FONT_HEIGHT;
        }
    }
    public static int getFontHeight() {
        return getFontHeight(ModuleManager.getModule(CustomFont.class).isEnabled());
    }
    public static String trimStringToWidth(String substring, int width)
    {
        return mc.fontRenderer.trimStringToWidth(substring, width);
    }

    public static String trimStringToWidth(String text, int j, boolean b)
    {
        return mc.fontRenderer.trimStringToWidth(text, j, b);
    }
}