package me.ritomg.ananta.module.modules.misc;

import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.settings.NumberSetting;
import me.ritomg.ananta.setting.settings.StringSetting;
import me.ritomg.ananta.util.misc.AnantaMessageUtil;
import me.ritomg.ananta.util.misc.Timer;

@Module.Info(name = "AutoSpam", description = "sends messages to server automatically", category = Category.Misc)
public class AutoSpam extends Module {

    StringSetting message = addStringSetting("Message", "Download ananta");
    NumberSetting delay = addIntegerSetting("Delay(Second)", 1,5,10);

    Timer timer = new Timer();

    public void onUpdate() {
        if (timer.passed(delay.getCurrent() * 1000L)) {
            AnantaMessageUtil.sendServerMessage(message.getText());
            timer.reset();
        }
    }
}
