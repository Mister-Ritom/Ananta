package me.ritomg.ananta.util.misc;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.ritomg.ananta.hud.HudManager;
import me.ritomg.ananta.notifcation.Notification;
import me.ritomg.ananta.notifcation.NotificationManager;
import me.ritomg.ananta.util.Util;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.util.text.TextComponentString;

public class AnantaMessageUtil extends Util {

    public static String Ananta = ChatFormatting.GRAY + "[" + ChatFormatting.DARK_PURPLE + "Ananta" + ChatFormatting.LIGHT_PURPLE+ "Client" + ChatFormatting.GRAY + "] " + ChatFormatting.RESET;

    public static void sendClientPrefixMessage(String message) {
        if (mc.player != null)
            if (!HudManager.getHud(me.ritomg.ananta.hud.huds.Notification.class).isEnabled() && !((me.ritomg.ananta.hud.huds.Notification)HudManager.getHud(me.ritomg.ananta.hud.huds.Notification.class)).stopChat.isOn()) {
                TextComponentString string1 = new TextComponentString(Ananta + ChatFormatting.GRAY + message);
                if (mc.player != null && mc.world !=null)
                    mc.player.sendMessage(string1);
            }
        NotificationManager.addNotification(new Notification(message));
    }


    public static void sendServerMessage(String message, ChatFormatting formatting) {
        if (mc.player != null && mc.world !=null)
            player.connection.sendPacket(new CPacketChatMessage(formatting + message));
    }
    public static void sendServerMessage(String message) {
        if (mc.player != null && mc.world !=null)
//            player.connection.sendPacket(new CPacketChatMessage(ChatFormatting.GRAY + message));
            mc.player.sendChatMessage(message);
    }

}
