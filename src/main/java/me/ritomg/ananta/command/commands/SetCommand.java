package me.ritomg.ananta.command.commands;

import me.ritomg.ananta.command.Command;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.setting.Setting;
import me.ritomg.ananta.setting.settings.DNumberSetting;
import me.ritomg.ananta.setting.settings.ModeSetting;
import me.ritomg.ananta.setting.settings.NumberSetting;

public class SetCommand extends Command {
//Doesn't work currently
    public SetCommand() {
        super("Set", "Sets value of a setting. types - string,int,double,mode", new String[]{"set", "setSetting"}, "Set {module} {type} {setting} {value}");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if (args.length == 4) {
            String modulename = args[0];
            String settingname = args[1];
            String type  = args[2];
            String value = args[3];
            Module module = ModuleManager.getModule(modulename);
            assert module != null;
            Setting setting = module.getSettingByName(settingname);

            if (ModuleManager.getModules().contains(module)) {
                if (module.getSettings().contains(setting)) {
                    switch (type.toLowerCase()) {
                        case "string" :
                            ((StringSetting)setting).setText(value);
                            break;
                        case "int" :
                            int val = Integer.parseInt(value);
                            ((NumberSetting)setting).setCurrent(val);
                            break;
                        case "double" :
                            double dval  = Double.parseDouble(value);
                            ((DNumberSetting)setting).setCurrent(dval);
                            break;
                        case "mode" :
                            ((ModeSetting)setting).setCurrentMode(value);
                            break;
                    }
                }
            }

        }
    }
}
