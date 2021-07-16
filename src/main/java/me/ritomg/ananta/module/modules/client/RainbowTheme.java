package me.ritomg.ananta.module.modules.client;

import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;

@Module.Info(name = "RainbowTheme", description = "Change color of Rainbow Theme", category = Category.Client)
public class RainbowTheme extends Module {

    public void onEnable() {
        ModuleManager.getModule(ClickGui.class).theme.setCurrentMode("RainbowTheme");
    }
}
