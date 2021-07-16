package me.ritomg.ananta.module.modules.client;

import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;

@Module.Info(name = "ImpactTheme", description = "Change color of Impact Theme", category = Category.Client)
public class ImpactTheme extends Module {

    public void onEnable() {
        ModuleManager.getModule(ClickGui.class).theme.setCurrentMode("ImpactTheme");
    }
}
