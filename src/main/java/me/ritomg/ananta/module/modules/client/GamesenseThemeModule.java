package me.ritomg.ananta.module.modules.client;

import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;

@Module.Info(name = "GamesenseTheme", description = "Change colors of GamesenseTheme", category = Category.Client)
public class GamesenseThemeModule extends Module {

    public void onEnable() {
        ModuleManager.getModule(ClickGui.class).theme.setCurrentMode("GamesenseTheme");
    }
}
