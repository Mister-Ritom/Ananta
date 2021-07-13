package me.ritomg.ananta.module.modules.render;

import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;

@Module.Info(name = "FullBright", description = "Increase your gamma to see in darkness", category = Category.Render)
public class FullBright extends Module {

    float oldgamma;

    public void onEnable() {
        oldgamma = mc.gameSettings.gammaSetting;
    }

    public void onUpdate() {
        mc.gameSettings.gammaSetting = 666f;
    }

    public void onDisable() {
        mc.gameSettings.gammaSetting = oldgamma;
    }
}
