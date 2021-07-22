package me.ritomg.ananta.module.modules.misc;

import java.util.Random;

import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.util.EnumHand;

@Module.Info(category = Category.Misc, name = "AntiAfk", description = "Moves in order not to get kicked. (May be invisible client-sided)")
public class AntiAfk extends Module{

	public BooleanSetting swing = addBooleanSetting("Swing", true);
	public BooleanSetting turn = addBooleanSetting("Turn", true);
	public Random random = new Random();
	
	public void onUpdate() {
		if (mc.playerController.getIsHittingBlock()) {
			return;
		}
		if (mc.player.ticksExisted % 40 == 0 && swing.isOn())
            mc.getConnection().sendPacket(new CPacketAnimation(EnumHand.MAIN_HAND));
        if (mc.player.ticksExisted % 15 == 0 && turn.isOn())
            mc.player.rotationYaw = random.nextInt(360) - 180;

        if (!(swing.isOn() || turn.isOn()) && mc.player.ticksExisted % 80 == 0) {
            mc.player.jump();
        }
	}
	
}
