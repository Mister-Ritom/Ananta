package me.ritomg.ananta.module.modules.client;

import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.settings.BooleanSetting;
import me.ritomg.ananta.setting.settings.SettingGroup;
import me.ritomg.ananta.util.misc.AnantaMessageUtil;

@Module.Info(name = "A test", description = "", category = Category.Client)
public class ATestModule extends Module {


    public SettingGroup aTestGroup = new SettingGroup.SettingGroupBuilder()
            .withParent(this)
            .withName("Test Group")
            .withDescription("Nothing just a test")
            .build();

    BooleanSetting setting = (BooleanSetting) aTestGroup.addSubSetting(new BooleanSetting.BooleanSettingBuilder()
            .withParent(this)
            .withName("a test")
            .withIsOn(false)
            .withDescription("second test")
            .build(true));


    @Override
    public void onEnable() {
        if (setting.isOn()) {
            AnantaMessageUtil.sendCommandMessage("hi");
        }
    }
}
