package me.ritomg.ananta.module.modules.client;

import me.ritomg.ananta.Ananta;
import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.settings.BooleanSetting;
import org.lwjgl.input.Keyboard;

@Module.Info(name = "ClickGui", description = "Open the gui", category = Category.Client, bind = Keyboard.KEY_RSHIFT)
public class ClickGui extends Module {

    public void onEnable() {
        Ananta.logger.info("ClickGui Started");
        disable();
    }

    public void onDisable() {

    }
}
