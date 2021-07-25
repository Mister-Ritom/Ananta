package me.ritomg.ananta.module.modules.misc;

import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.settings.NumberSetting;
import me.ritomg.ananta.setting.settings.StringSetting;
import me.ritomg.ananta.util.misc.AnantaMessageUtil;
import me.ritomg.ananta.util.misc.Timer;

@Module.Info(name = "AutoSpam", description = "sends messages to server automatically", category = Category.Misc)
public class AutoSpam extends Module {

    StringSetting message = new StringSetting.StringSettingBuilder()
            .withParent(this)
            .withName("Message")
            .withDescription("The message to be sent")
            .withText( "Download Ananta the best client")
            .build();
    NumberSetting delay = new NumberSetting.NumberSettingBuilder()
            .withParent(this)
            .withName("Delay")
            .withDescription("The delay to send message in seconds")
            .withCurrent(5)
            .withMax(10)
            .withMin(1)
            .build();

    Timer timer = new Timer();

    public void onUpdate() {
        if (timer.passed(delay.getCurrent() * 1000L)) {
            AnantaMessageUtil.sendServerMessage(message.getText());
            timer.reset();
        }
    }
}
