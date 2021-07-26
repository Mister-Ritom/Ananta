package me.ritomg.ananta.module.modules.chat;

import me.ritomg.ananta.event.events.PacketEvent;
import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.settings.ModeSetting;
import me.ritomg.ananta.util.mapping.MappingsSetter;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Arrays;

import com.mojang.realmsclient.gui.ChatFormatting;

@Module.Info(category = Category.Chat, description = "Makes your chat messages coloured", name = "ColouredChat")
public class ColouredChat extends Module{

	public ModeSetting mode  = new ModeSetting.ModeSettingBuilder()
			.withParent(this)
			.withName("Colour")
			.withDescription("which colour should your message convert to?")
			.withCurrentMode("Green")
			.withModes(Arrays.asList("Green", "Blue"))
			.build();
//	@SubscribeEvent
//	public void onMessageSend (PacketEvent event) {
//		if(event.getPacket() instanceof CPacketChatMessage) {
//			CPacketChatMessage packet = (CPacketChatMessage) event.getPacket();
//			if(packet.getMessage().startsWith("/") || packet.getMessage().startsWith(".") || packet.getMessage().startsWith(">") || packet.getMessage().startsWith("`")) return;
//			MappingsSetter.setMessage(mode.is("Green")? convertToGreen(packet.getMessage()) : convertToBlue(packet.getMessage()), packet);
//		}
//	}
//	
//	public String convertToGreen(String s) {
//		return ChatFormatting.valueOf("GREEN") + s;
//	}
//	
//	public String convertToBlue(String s) {
//		return "`" + s;
//	}
	
}
