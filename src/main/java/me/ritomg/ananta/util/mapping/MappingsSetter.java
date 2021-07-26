package me.ritomg.ananta.util.mapping;

import java.lang.reflect.Field;

import me.ritomg.ananta.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.util.Timer;

public class MappingsSetter extends Util{

	public static void setTimer(float value) {
		try {
			
			Field timer = Minecraft.class.getDeclaredField(MappingUtil.timer);
			timer.setAccessible(true);
			Field tickLenth = Timer.class.getDeclaredField(MappingUtil.tickLength);
			tickLenth.setAccessible(true);
			tickLenth.setFloat(timer.get(mc), 50F/value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void setMessage(String newMessage, CPacketChatMessage instance) {
		try {
			Field message  = CPacketChatMessage.class.getDeclaredField(MappingUtil.cPacketChatMessageMessage);
			message.setAccessible(true);
			message.set(instance, newMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
