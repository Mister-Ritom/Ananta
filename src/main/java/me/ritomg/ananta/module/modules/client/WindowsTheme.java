package me.ritomg.ananta.module.modules.client;

import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;

@Module.Info(name = "WindowsTheme", description = "Change color of Windows Theme", category = Category.Client)
public class WindowsTheme extends Module {

    public void onEnable() {
        ModuleManager.getModule(ClickGui.class).theme.setCurrentMode("WindowsTheme");
    }

}
