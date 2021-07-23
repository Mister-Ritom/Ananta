package me.ritomg.ananta.module.modules.combat;

import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.settings.BooleanSetting;
import me.ritomg.ananta.setting.settings.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.util.EnumHand;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Module.Info(name = "KillAura", description = "Automatically attacks targets", category = Category.Combat)
public class KillAura extends Module {

    public BooleanSetting players = addBooleanSetting("Players", true);
    public BooleanSetting mobs  = addBooleanSetting("Mobs", false);
    BooleanSetting cooldown = addBooleanSetting("CoolDown", false);
    public BooleanSetting animals = addBooleanSetting("Animals", false);
    public NumberSetting range = addIntegerSetting("Range", 1,6,15);

    public void onUpdate() {
        if (mc.player == null || mc.player.isDead)return;
        List<Entity> targets = mc.world.loadedEntityList.stream()
                .filter(entity -> entity != mc.player)
                .filter(entity -> mc.player.getDistance(entity) <= range.getCurrent())
                .filter(entity -> !entity.isDead)
                .sorted(Comparator.comparing(s -> mc.player.getDistance(s)))
                .collect(Collectors.toList());

        targets.forEach(this::attack);
    }

    public void attack(Entity e) {
        if (isValidTargt(e)) {
            if (cooldown.isOn()) {
                if (mc.player.getCooledAttackStrength(0) >= 1) {
                    mc.playerController.attackEntity(mc.player, e);
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                }
            } else {
                mc.playerController.attackEntity(mc.player, e);
                mc.player.swingArm(EnumHand.MAIN_HAND);
                mc.player.connection.sendPacket(new CPacketAnimation());
            }
        }
    }

    public boolean isValidTargt(Entity e) {
       if (players.isOn() && e instanceof EntityPlayer) return true;
       if (mobs.isOn() && e instanceof EntityMob) return true;
       if (animals.isOn() && e instanceof EntityAnimal) return true;
       return false;
    }


}
