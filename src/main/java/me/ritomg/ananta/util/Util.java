package me.ritomg.ananta.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.world.World;

public abstract class Util {

    protected static Minecraft mc = Minecraft.getMinecraft();
    protected static EntityPlayerSP player = mc.player;
    protected static World world = mc.world;

}
