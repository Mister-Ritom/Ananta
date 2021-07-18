package me.ritomg.ananta.module.modules.client;

import me.ritomg.ananta.Ananta;
import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.settings.BooleanSetting;
import me.ritomg.ananta.setting.settings.ModeSetting;
import me.ritomg.ananta.setting.settings.NumberSetting;
import me.ritomg.ananta.util.font.CFontRenderer;

import java.awt.*;
import java.util.Arrays;

@Module.Info(name = "CustomFont", description = "Changes ingame font", category = Category.Client)
public class CustomFont extends Module {

    BooleanSetting antialias = addBooleanSetting("AntiAlias", true);
    ModeSetting mode = addModeSetting("FontMode", "Italic", Arrays.asList("Italic", "Bold", "Plain", "BoldItalic"));
    ModeSetting font = addModeSetting("Font", "Verdana",Arrays.asList("Comic Sans Ms", "Arial", "Verdana", "Courier", "TimesRoman", "Tahoma", "Serif"));
    NumberSetting height = addIntegerSetting("FontSize", 10,18,36);

    public void onEnable() {
            Ananta.customFont = new CFontRenderer(new Font(font.getCurrentMode(),mode.is("Bold")? Font.BOLD : mode.is("Plain") ? Font.PLAIN : Font.ITALIC, height.getCurrent()), antialias.isOn(), true);
    }

}
