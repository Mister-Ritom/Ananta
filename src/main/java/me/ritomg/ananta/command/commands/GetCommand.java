package me.ritomg.ananta.command.commands;

import me.ritomg.ananta.command.Command;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.setting.settings.ModeSetting;
import me.ritomg.ananta.util.AnantaMessageUtil;

import java.util.List;

public class GetCommand extends Command {

    public GetCommand() {
        super("Get", "gets modes of a modesetting (orignally created to check fonts of custom font)", new String[]{"get,getmodes,getmode"}, "get {module} {modesettinh}");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if (args.length == 2) {
            String moduleName = args[0];
            String settingName = args[1];
            Module module  = ModuleManager.getModule(moduleName);
            if (ModuleManager.getModules().contains(module)) {
                if (module.getSettingByName(settingName) != null) {
                    List<String> modes = ((ModeSetting) module.getSettingByName(settingName)).getModes();
                    if (modes != null)
                        for (String mode : modes) {
                            AnantaMessageUtil.sendClientPrefixMessage(mode);
                        }
                }
                else AnantaMessageUtil.sendClientPrefixMessage("404 Wrong name of setting for " + moduleName);
            }
            else AnantaMessageUtil.sendClientPrefixMessage("404 wrong name of module");
        }
        else sendWrongCommand();
    }
}
