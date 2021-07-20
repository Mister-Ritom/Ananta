package me.ritomg.ananta.module.modules.theme;

import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.module.modules.client.ClickGui;

@Module.Info(name = "ClearTheme", description = "Change color of Clear Theme", category = Category.Client)
public class ClearTheme extends Module {

    public void onEnable() {
        ModuleManager.getModule(ClickGui.class).theme.setCurrentMode("ClearGradientTheme");
    }
}
