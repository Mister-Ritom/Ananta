package me.ritomg.ananta.module.modules.chat;

import me.ritomg.ananta.event.events.PacketEvent;
import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.util.mapping.MappingsSetter;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Module.Info(category = Category.Chat, description = "converts your chatmessages to unicode", name = "CustomChat")
public class CustomChat extends Module{

	
	@SubscribeEvent
	public void onMessageSend (PacketEvent event) {
		if(event.getPacket() instanceof CPacketChatMessage) {
			CPacketChatMessage packet = (CPacketChatMessage) event.getPacket();
			if(packet.getMessage().startsWith("/") || packet.getMessage().startsWith(".")) return;
			if(packet.getMessage() == new ChatSuffix().toUnicode(packet.getMessage())) return;
			MappingsSetter.setMessage(new ChatSuffix().toUnicode(packet.getMessage()), packet);
		}
	}
	
	
}
