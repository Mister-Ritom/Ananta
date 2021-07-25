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

@Module.Info(name = "ESP", description = "draws lines around targets", category = Category.Render)
public class ESP extends Module {

    public BooleanSetting players = new BooleanSetting.BooleanSettingBuilder()
            .withParent(this)
            .withName("Players")
            .withDescription("Should players be rendered?")
            .withIsOn(true)
            .build();
    public BooleanSetting mobs  = new BooleanSetting.BooleanSettingBuilder()
            .withParent(this)
            .withName("Mobs")
            .withDescription("Should monsters be rendered?")
            .withIsOn(true)
            .build();
    public BooleanSetting animals = new BooleanSetting.BooleanSettingBuilder()
            .withParent(this)
            .withName("Animals")
            .withDescription("Should animals be rendered?")
            .withIsOn(true)
            .build();
    public BooleanSetting others = new BooleanSetting.BooleanSettingBuilder()
            .withParent(this)
            .withName("Others")
            .withDescription("Should animals be rendered?")
            .withIsOn(true)
            .build();
    ColourSetting color = new ColourSetting.ColourSettingBuilder()
            .withParent(this)
            .withName("PlayerColor")
            .withDescription("The color for player ESP")
            .withColor(new Color(255,0,0))
            .withIsVisible(()->players.isOn())
            .build();
    ColourSetting color2 = new ColourSetting.ColourSettingBuilder()
            .withParent(this)
            .withName("MobsColor")
            .withDescription("The color for monsters ESP")
            .withColor(new Color(0,0,255))
            .withIsVisible(()->mobs.isOn())
            .build();
    ColourSetting color3 = new ColourSetting.ColourSettingBuilder()
            .withParent(this)
            .withName("AnimalsColor")
            .withDescription("The color for animals ESP")
            .withColor(new Color(0,255,0))
            .withIsVisible(()->animals.isOn())
            .build();
    ColourSetting othersColour = new ColourSetting.ColourSettingBuilder()
            .withParent(this)
            .withName("OthersColor")
            .withDescription("The color for Other entity ESP")
            .withColor(new Color(255,0,0))
            .withIsVisible(()->others.isOn())
            .build();

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
