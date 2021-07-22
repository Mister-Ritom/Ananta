package me.ritomg.ananta.module.modules.movement;

import me.ritomg.ananta.event.events.PacketEvent;
import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//:notSkid:
@Module.Info(name = "PlayerTweaks", description = "Changes various things about player", category = Category.Movement)
public class PlayerTweaks extends Module {

    BooleanSetting velocity = addBooleanSetting("Velocity", false);

    @SubscribeEvent
    public void OnVeloctiy(PacketEvent.Receive event) {
        if (velocity.isOn()) {
            if (event.getPacket() instanceof SPacketEntityVelocity) {
                if (((SPacketEntityVelocity) event.getPacket()).getEntityID() == mc.player.getEntityId()) {
                    event.setCanceled(true);
                }
            }
            if (event.getPacket() instanceof SPacketExplosion) {
                event.setCanceled(true);
            }
        }
    }

}
