package me.ritomg.ananta.module.modules.client;

import com.lukflug.panelstudio.base.AnimatedToggleable;
import me.ritomg.ananta.Ananta;
import me.ritomg.ananta.gui.RaptorClientGui;
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
    public ModeSetting theme = addModeSetting("Theme", "GamesenseTheme",Arrays.asList("RainbowTheme", "ClearGradientTheme", "GamesenseTheme", "ClearTheme", "Windows"));
    public ModeSetting layout = addModeSetting("Layout", "Normal", Arrays.asList("Normal", "CSGO", "Search", "Single"));
    public BooleanSetting ignoreDisabled = addBooleanSetting("IgnoreDisabled", true);
    public BooleanSetting buttonRainbow = addBooleanSetting("ButtonRainbow", false);
    public NumberSetting animationSpeed = addIntegerSetting("AnimationSpeed", 0,200,1000);

    public void onEnable() {
        Ananta.INSTANCE.gui.enterGUI();
        disable();
    }

    public void onDisable() {

    }
}
