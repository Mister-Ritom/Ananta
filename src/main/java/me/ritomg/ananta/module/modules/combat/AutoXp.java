package me.ritomg.ananta.module.modules.combat;

import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Items;

@Module.Info(name = "AutoXP", description = "Automatically presses keybind to use xp bottles", category = Category.Combat)
public class AutoXp extends Module {

    public void onUpdate() {
        if (mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE || mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE) {
            int key = mc.gameSettings.keyBindUseItem.getKeyCode();
            KeyBinding.setKeyBindState(key,true);
        }
    }
}
