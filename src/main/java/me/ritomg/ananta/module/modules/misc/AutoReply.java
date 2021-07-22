package me.ritomg.ananta.module.modules.misc;

import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.util.misc.AnantaMessageUtil;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Module.Info(name = "AutoReply", description = "Automatically replies to players that message you", category = Category.Misc)
public class AutoReply extends Module {

    public StringSetting message = addStringSetting("Message", "i don't speak newfags!");

    @SubscribeEvent
    public void onMessageRecive(ClientChatReceivedEvent event) {
        if (event.getMessage().getUnformattedText().contains("whispers: ") && !event.getMessage().getUnformattedText().startsWith(mc.player.getName())) {
            if (event.getMessage().getUnformattedText().contains("I don't speak to newfags!")) {
                return;
            }

            AnantaMessageUtil.sendServerMessage("/r" + message.getText());
        }
    }

}
