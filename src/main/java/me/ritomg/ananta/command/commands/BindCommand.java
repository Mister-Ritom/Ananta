package me.ritomg.ananta.command.commands;

import me.ritomg.ananta.command.Command;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.util.AnantaMessageUtil;
import org.lwjgl.input.Keyboard;

public class BindCommand extends Command {
    public BindCommand() {
        super("Bind", "sets modules binds", new String[]{"Bind", "setBind"}, "bind 'module" );
    }

    @Override
    public void onCommand(String[] args, String command) {
        if (args.length == 2) {
            String module = args[0];
            String bind = args[1];
            int key;
            if (bind.equalsIgnoreCase("none")) key = 0;
            else key = Keyboard.getKeyIndex(bind.toUpperCase());
            if (ModuleManager.getModule(module) == null) {
                AnantaMessageUtil.sendClientPrefixMessage(module + " not found");
            }
            else {
                ModuleManager.getModule(module).setBind(key);
            }
        }
    }
}
