package me.ritomg.ananta.event;

import me.ritomg.ananta.command.CommandManager;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class EventProcessor {

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyEvent(InputEvent.KeyInputEvent event) {
        if (!Keyboard.getEventKeyState() || Keyboard.getEventKey() == Keyboard.KEY_NONE) return;

        int key = Keyboard.getEventKey();

        for (Module m : ModuleManager.getModules()) {
            if (key != Keyboard.KEY_NONE) {
                if (m.getBind() == key)
                    m.toggle();
            }
        }

    }

    @SubscribeEvent
    public void onUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        if (Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().world == null) {
            return;
        }
        if (event.getEntity().getEntityWorld().isRemote && event.getEntityLiving() == Minecraft.getMinecraft().player) {
            for (Module m : ModuleManager.getModules()) {
                if (m.isEnabled()) {
                    m.onUpdate();
                }
            }
        }
    }

    @SubscribeEvent
    public void onChatSent(ClientChatEvent event) {
        if (event.getMessage().startsWith(CommandManager.prefix)) {
            event.setCanceled(true);
            CommandManager.callCommand(event.getMessage());
        }
    }

}