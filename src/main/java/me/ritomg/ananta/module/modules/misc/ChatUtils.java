package me.ritomg.ananta.module.modules.misc;

import me.ritomg.ananta.event.events.PacketEvent;
import me.ritomg.ananta.mixin.mixins.accessor.CPacketChatMessageAccessor;
import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.settings.BooleanSetting;
import me.ritomg.ananta.setting.settings.ModeSetting;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;

@Module.Info(name = "ChatUtils", description = "Chat things", category = Category.Misc)
public class ChatUtils extends Module {

    BooleanSetting suffix = addBooleanSetting("Suffix", false);
    ModeSetting Separator = addModeSetting("Separator", "|",Arrays.asList(">>", "<<", "|"));

    @SubscribeEvent
    public void onChatPacket(PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketChatMessage) {
            if (((CPacketChatMessage) event.getPacket()).getMessage().startsWith("/")) return;
            if (suffix.isOn())
            ((CPacketChatMessageAccessor)event.getPacket()).setMessage(((CPacketChatMessage) event.getPacket()).getMessage() + getSeparator() + toUnicode("AnantaClient"));
        }
    }

    private String toUnicode(String s) {
        return s.toLowerCase()
                .replace("a", "\u1d00")
                .replace("b", "\u0299")
                .replace("c", "\u1d04")
                .replace("d", "\u1d05")
                .replace("e", "\u1d07")
                .replace("f", "\ua730")
                .replace("g", "\u0262")
                .replace("h", "\u029c")
                .replace("i", "\u026a")
                .replace("j", "\u1d0a")
                .replace("k", "\u1d0b")
                .replace("l", "\u029f")
                .replace("m", "\u1d0d")
                .replace("n", "\u0274")
                .replace("o", "\u1d0f")
                .replace("p", "\u1d18")
                .replace("q", "\u01eb")
                .replace("r", "\u0280")
                .replace("s", "\ua731")
                .replace("t", "\u1d1b")
                .replace("u", "\u1d1c")
                .replace("v", "\u1d20")
                .replace("w", "\u1d21")
                .replace("x", "\u02e3")
                .replace("y", "\u028f")
                .replace("z", "\u1d22");
    }

    public String getSeparator() {
        switch (Separator.getCurrentMode()) {
            case ">>" : return" \u300b";
            case "<<" : return " \u300a";
            default: return " \u23D0 ";
        }
    }

}
