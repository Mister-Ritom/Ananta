package me.ritomg.ananta.module.modules.client;

import me.ritomg.ananta.Ananta;
import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.Setting;
import me.ritomg.ananta.setting.settings.BooleanSetting;
import me.ritomg.ananta.setting.settings.ModeSetting;
import me.ritomg.ananta.setting.settings.NumberSetting;
import me.ritomg.ananta.util.font.CFontRenderer;

import java.awt.*;
import java.util.Arrays;

@Module.Info(name = "CustomFont", description = "Changes ingame font", category = Category.Client)
public class CustomFont extends Module {

    BooleanSetting antialias = new BooleanSetting.BooleanSettingBuilder()
        .withParent(this)
        .withName("AntiAlias")
        .withDescription("IDK font stuff lol")
        .withIsOn(true)
        .build();
    ModeSetting mode = new ModeSetting.ModeSettingBuilder()
            .withParent(this)
            .withName("FontMode")
            .withCurrentMode("Italic")
            .withDescription("the font mode")
            .withModes(Arrays.asList("Italic", "Bold", "Plain", "BoldItalic"))
            .build();
//    ModeSetting font = addModeSetting("Font", "Verdana",Arrays.asList("Comic Sans Ms", "Arial", "Verdana", "Courier", "TimesRoman", "Tahoma", "Serif"));
    ModeSetting font = new ModeSetting.ModeSettingBuilder()
        .withParent(this)
        .withName("Font")
        .withDescription("the font that should be used. There's too many options lol")
        .withCurrentMode("Verdana")
        .withModes(Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()))
        .build();
    NumberSetting height = new NumberSetting.NumberSettingBuilder()
            .withParent(this)
            .withName("FontSize")
            .withDescription("FontSize what do you expect?")
            .withCurrent(18)
            .withMax(36)
            .withMin(10)
            .build();

    public void onEnable() {
            Ananta.customFont = new CFontRenderer(new Font(font.getCurrentMode(),Font.ITALIC, height.getCurrent()), antialias.isOn(), true);
    }

    public void onSettingChange(Module m, Setting s) {
        if (m.equals(this)) {
            Ananta.customFont = new CFontRenderer(new Font(font.getCurrentMode(),mode.is("Bold")? Font.BOLD : mode.is("Plain") ? Font.PLAIN : mode.is("BoldItalic")? Font.ITALIC + Font.BOLD : Font.ITALIC, height.getCurrent()), antialias.isOn(), true);
        }
    }

}
