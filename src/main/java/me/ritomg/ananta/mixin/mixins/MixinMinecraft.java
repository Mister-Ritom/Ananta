package me.ritomg.ananta.mixin.mixins;

import me.ritomg.ananta.config.SaveConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {

//     Sponsored by KAMI Blue
 //    https:github.com/kami-blue/client/blob/97a62ce0a3e165f445e46bc6ea0823020d1b14ae/src/main/java/org/kamiblue/client/mixin/client/MixinMinecraft.java#L84

    @Inject(method = "crashed", at = @At("HEAD"))
    public void crashed(CrashReport crash, CallbackInfo callbackInfo) {
        SaveConfig.init();
    }

    @Inject(method = "shutdown", at = @At("HEAD"))
    public void shutdown(CallbackInfo callbackInfo) {
        SaveConfig.init();
    }
}