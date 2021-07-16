package me.ritomg.ananta.util;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.util.text.TextComponentString;

public class AnantaMessageUtil {

    public static String Ananta = ChatFormatting.GRAY + "[" + ChatFormatting.DARK_AQUA + "Ananta" + ChatFormatting.AQUA + "Client" + ChatFormatting.GRAY + "] " + ChatFormatting.RESET;

    protected static final Minecraft mc = Minecraft.getMinecraft();

    /**
     * Sends a client-sided message WITH the client prefix
     **/
    public static void sendClientPrefixMessage(String message, ChatFormatting messageFormatting) {
        TextComponentString string1 = new TextComponentString(Ananta + messageFormatting + message);
        TextComponentString string2 = new TextComponentString(messageFormatting + message);
        mc.player.sendMessage(string1);
    }

    public static void sendClientPrefixMessage(String message) {
        TextComponentString string1 = new TextComponentString(Ananta + ChatFormatting.GRAY + message);
        mc.player.sendMessage(string1);
    }

    public static void sendClientRawMessage(String message, ChatFormatting messageFormatting) {
        TextComponentString string = new TextComponentString(messageFormatting + message);

        mc.player.sendMessage(string);
    }

    /**
     * Sends a server-sided message
     **/
    public static void sendServerMessage(String message, ChatFormatting formatting) {
        mc.player.connection.sendPacket(new CPacketChatMessage(formatting + message));
    }
    public static void sendServerMessage(String message) {
        mc.player.connection.sendPacket(new CPacketChatMessage(ChatFormatting.GRAY + message));
    }


}
