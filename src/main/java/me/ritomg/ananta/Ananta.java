package me.ritomg.ananta;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(
        modid = Ananta.MOD_ID,
        name = Ananta.MOD_NAME,
        version = Ananta.VERSION
)
public class Ananta {

    public static final String MOD_ID = "ananta";
    public static final String MOD_NAME = "Ananta";
    public static final String VERSION = "0.1";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */

    public Ananta() {
        INSTANCE = this;
    }

    @Mod.Instance(MOD_ID)
    public static Ananta INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {

    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }
}
