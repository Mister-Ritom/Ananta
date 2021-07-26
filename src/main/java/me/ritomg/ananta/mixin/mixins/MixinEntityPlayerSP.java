package me.ritomg.ananta.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.ritomg.ananta.event.events.MessageSendEvent;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.common.MinecraftForge;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP {

    @Shadow public abstract void sendChatMessage(String string);
    
    @Inject(method={"sendChatMessage"}, at={@At(value="HEAD")}, cancellable=true)
    public void sendChatMessage(String message, CallbackInfo callback) {
        
        
        MessageSendEvent event = new MessageSendEvent(message);
        MinecraftForge.EVENT_BUS.post(event);
       
        	if (event.isCanceled()) {
            callback.cancel();
        	}
        
    }
	
}
