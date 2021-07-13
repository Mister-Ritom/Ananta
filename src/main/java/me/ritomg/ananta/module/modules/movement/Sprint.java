package me.ritomg.ananta.module.modules.movement;

import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import net.minecraft.client.settings.KeyBinding;

@Module.Info(name = "Sprint", description = "Automatically presses the sprint button", category = Category.Movement)
public class Sprint extends Module {

    public void onUpdate() {
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(),true);
    }

    public void onDisable() {
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(),false);
    }
}
