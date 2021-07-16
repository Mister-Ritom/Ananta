package me.ritomg.ananta.module.modules.client;

import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;

@Module.Info(name = "ToggleMessage", description = "sets all the modules toggle message", category = Category.Client)
public class ToggleMessage extends Module {

    public void onEnable() {
        ModuleManager.getModules().forEach(module -> {
            module.toggleMessage = !module.toggleMessage;
        });
    }

    public void onDisable() {
        ModuleManager.getModules().forEach(module -> {
            module.toggleMessage = !module.toggleMessage;
        });
    }
}
