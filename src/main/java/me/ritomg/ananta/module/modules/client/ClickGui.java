package me.ritomg.ananta.module.modules.client;

import me.ritomg.ananta.Ananta;
import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.settings.BooleanSetting;
import me.ritomg.ananta.setting.settings.ModeSetting;
import me.ritomg.ananta.setting.settings.NumberSetting;
import org.lwjgl.input.Keyboard;

import java.util.Arrays;

@Module.Info(name = "ClickGui", description = "Open the gui", category = Category.Client, bind = Keyboard.KEY_RSHIFT)
public class ClickGui extends Module {

    public NumberSetting scrollSpeed = addIntegerSetting("ScrollSpeed", 1,2,20);
    public ModeSetting theme = addModeSetting("Theme", "GamesenseTheme",Arrays.asList("RainbowTheme", "ClearGradientTheme", "GamesenseTheme", "ClearTheme", "Windows","ImpactTheme"));
    public ModeSetting scrolling = addModeSetting("Scrolling", "Screen",Arrays.asList("Screen", "Container"));
    public ModeSetting layout = addModeSetting("Layout", "Normal", Arrays.asList("Normal", "CSGO", "Search", "Single","Stacked"));
    public BooleanSetting ignoreDisabled = new BooleanSetting("IgnoreDisabled",this, theme.is("RainbowTheme"),true);
    public BooleanSetting buttonRainbow = new BooleanSetting("ButtowRainbow",this,theme.is("RainbowTheme"),false);
    public NumberSetting animationSpeed = addIntegerSetting("AnimationSpeed", 0,200,1000);

    public ClickGui() {
        addSetting(buttonRainbow);
        addSetting(ignoreDisabled);
    }

    public void onEnable() {
        Ananta.INSTANCE.gui.enterGUI();
    }

    public void onDisable() {

    }
}
