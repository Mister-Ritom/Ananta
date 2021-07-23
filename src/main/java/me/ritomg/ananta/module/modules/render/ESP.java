package me.ritomg.ananta.module.modules.render;

import me.ritomg.ananta.config.LoadConfig;
import me.ritomg.ananta.event.events.RenderEvent;
import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.Setting;
import me.ritomg.ananta.setting.settings.BooleanSetting;
import me.ritomg.ananta.setting.settings.ColourSetting;
import me.ritomg.ananta.util.render.RenderUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;

import java.awt.*;

@Module.Info(name = "ESP", description = "", category = Category.Render)
public class ESP extends Module {

    public BooleanSetting players = addBooleanSetting("Players", true);
    public BooleanSetting mobs  = addBooleanSetting("Mobs", false);
    public BooleanSetting animals = addBooleanSetting("Animals", false);
    public BooleanSetting others = addBooleanSetting("Others", false);
    ColourSetting color = addColorSetting("PlayerColor", new Color(255,0,0,50));
    ColourSetting color2 = addColorSetting("MobsColor", new Color(0,0,255));
    ColourSetting color3 = addColorSetting("AnimalsColor", new Color(0,255,0));
    ColourSetting othersColour = addColorSetting("OthersColour", new Color(255,255,255));

    @Override
    public void onWorldRender(RenderEvent event) {
        for (Entity e : mc.world.getLoadedEntityList()) {
            if(e instanceof EntityLivingBase) {
                if(e instanceof EntityPlayer) {
                    if(e != mc.player)
                        if (players.isOn())
                            RenderUtil.entityESPBox(e, color.getColor());
                    continue;
                }
                if(e instanceof EntityMob) {
                    if (mobs.isOn())
                        RenderUtil.entityESPBox(e, color2.getColor());
                    continue;
                }
                if(e instanceof EntityAnimal) {
                    if (animals.isOn())
                        RenderUtil.entityESPBox(e, color3.getColor());
                    continue;
                }
                if (others.isOn())
                    RenderUtil.entityESPBox(e, othersColour.getColor());
            }
        }
    }

    public void onSettingChange(Module m, Setting s) {
        if(LoadConfig.isLoading) return;
        if (m == this) {
            for (Entity e : mc.world.getLoadedEntityList()) {
                if(e instanceof EntityLivingBase) {
                    if(e instanceof EntityPlayer) {
                        if(e != mc.player)
                            if (players.isOn())
                                RenderUtil.entityESPBox(e, color.getColor());
                        continue;
                    }
                    if(e instanceof EntityMob) {
                        if (mobs.isOn())
                            RenderUtil.entityESPBox(e, color2.getColor());
                        continue;
                    }
                    if(e instanceof EntityAnimal) {
                        if (animals.isOn())
                            RenderUtil.entityESPBox(e, color3.getColor());
                        continue;
                    }
                    if (others.isOn())
                        RenderUtil.entityESPBox(e, othersColour.getColor());
                }
            }
        }
    }
}
