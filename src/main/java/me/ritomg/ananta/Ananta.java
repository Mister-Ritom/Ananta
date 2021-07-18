package me.ritomg.ananta;

import me.ritomg.ananta.command.CommandManager;
import me.ritomg.ananta.config.LoadConfig;
import me.ritomg.ananta.config.SaveConfig;
import me.ritomg.ananta.event.EventProcessor;
import me.ritomg.ananta.gui.AnantaClientGui;
import me.ritomg.ananta.hud.HudGui;
import me.ritomg.ananta.hud.HudManager;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.util.font.CFontRenderer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

@Mod(
        modid = Ananta.MOD_ID,
        name = Ananta.MOD_NAME,
        version = Ananta.VERSION
)
public class Ananta {

    public static final String MOD_ID = "ananta";
    public static final String MOD_NAME = "Ananta";
    public static final String VERSION = "0.1";
    public static final Logger logger = LogManager.getLogger("Ananta");

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */

    public Ananta() {
        INSTANCE = this;
    }

    public AnantaClientGui gui;
    public HudGui hudGui;
    public static CFontRenderer customFont;

    @Mod.Instance(MOD_ID)
    public static Ananta INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        logger.info("Starting Ananta");
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        StartClient();
    }

    public void StartClient() {
        ModuleManager.init();
        logger.info("Modules started");
        HudManager.init();
        logger.info("Huds Started");
        MinecraftForge.EVENT_BUS.register(new EventProcessor());
        gui = new AnantaClientGui();
        logger.info("Gui Started");
        hudGui = new HudGui();
        logger.info("Hud gui started");
        CommandManager.init();
        logger.info("Commands Started");
        LoadConfig.init();
        logger.info("Loaded Config");
        customFont = new CFontRenderer(new Font("Verdana", Font.BOLD, 18), true, true);
        Runtime.getRuntime().addShutdownHook(new ConfigStopper());
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        logger.info("Ananta Started");
    }
}

class ConfigStopper extends Thread{
    @Override
    public void run() {
        SaveConfig.init();
    }
}
