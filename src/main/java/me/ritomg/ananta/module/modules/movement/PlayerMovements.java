package me.ritomg.ananta.module.modules.movement;

import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.settings.BooleanSetting;
import net.minecraft.client.settings.KeyBinding;

@Module.Info(name = "PlayerMovements", description = "Automatically presses the sprint button", category = Category.Movement)
public class PlayerMovements extends Module {

    public BooleanSetting sprint = addBooleanSetting("Sprint", false);
    public BooleanSetting walk = addBooleanSetting("Walk", false);
    public BooleanSetting jump = addBooleanSetting("Jump", false);

    public void onUpdate() {

        if (sprint.isOn())
                KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(),true);
        if (walk.isOn())
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(),true);
        if (jump.isOn())
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindJump.getKeyCode(),true);
    }

    public void onDisable() {
        if (sprint.isOn())
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(),false);
        if (walk.isOn())
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(),false);
        if (jump.isOn())
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindJump.getKeyCode(),false);
    }
}
