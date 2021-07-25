package me.ritomg.ananta.module.modules.misc;

import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.settings.StringSetting;
import me.ritomg.ananta.util.misc.AnantaMessageUtil;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Module.Info(name = "AutoReply", description = "Automatically replies to players that message you", category = Category.Misc)
public class AutoReply extends Module {

    public StringSetting message = new StringSetting.StringSettingBuilder()
            .withParent(this)
            .withName("Message")
            .withDescription("The reply message")
            .withText( "i don't speak newfags!")
            .build();

    @SubscribeEvent
    public void onMessageRecive(ClientChatReceivedEvent event) {
        if (event.getMessage().getUnformattedText().contains("whispers: ") && !event.getMessage().getUnformattedText().startsWith(mc.player.getName())) {
            if (event.getMessage().getUnformattedText().contains(message.getText())) {
                return;
            }

            AnantaMessageUtil.sendServerMessage("/r" + message.getText());
        }
    }

}
