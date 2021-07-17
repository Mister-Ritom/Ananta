package me.ritomg.ananta.module.modules.client;

import me.ritomg.ananta.Ananta;
import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import org.lwjgl.input.Keyboard;

@Module.Info(name = "HudEditor", description = "Open the hud editor", category = Category.Client,bind = Keyboard.KEY_RCONTROL)
public class HudEditor extends Module {

    public void onEnable() {
        Ananta.INSTANCE.hudGui.enterGUI();
        disable();
    }
}
