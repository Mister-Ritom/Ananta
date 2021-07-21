package me.ritomg.ananta.module.modules.misc;

import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.settings.ModeSetting;
import net.minecraft.client.settings.KeyBinding;

import java.util.Arrays;

@Module.Info(name = "AutoClick", description = "Clicks buttons automatically", category = Category.Misc)
public class AutoClick extends Module {
    public ModeSetting button = addModeSetting("Button", "RightClick", Arrays.asList("RightClick", "LeftClick"));
    int key;
    public void onUpdate() {
        switch (button.getCurrentMode()) {
            case "RightClick" :
                key = mc.gameSettings.keyBindUseItem.getKeyCode();
                break;
            case "LeftClick" :
                key = mc.gameSettings.keyBindAttack.getKeyCode();
        }
        KeyBinding.setKeyBindState(key, true);
    }

    public void onDisable() {
        KeyBinding.setKeyBindState(key, false);
    }
}
