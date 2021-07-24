package me.ritomg.ananta.command.commands;

import me.ritomg.ananta.Ananta;
import me.ritomg.ananta.command.Command;
import me.ritomg.ananta.gui.AnantaClientGui;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.module.modules.client.ClickGui;
import me.ritomg.ananta.util.misc.AnantaMessageUtil;
import org.lwjgl.input.Keyboard;

public class FixGuiCommand extends Command {
    public FixGuiCommand() {
        super("FixGui", "sets gui postions and keybind to defalut", new String[]{"fixgui", "resetgui"}, "fixgui keybind/all/postion");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if (args.length == 1) {
           String arg = args[0];
           if (arg.equalsIgnoreCase("position")) {
               Ananta.gui = new AnantaClientGui();
               AnantaMessageUtil.sendCommandMessage("gui position reset ");
           }
           else if (arg.equalsIgnoreCase("keybind")) {
               ModuleManager.getModule(ClickGui.class).setBind(Keyboard.KEY_RSHIFT);
               AnantaMessageUtil.sendCommandMessage("gui keybind set to Rshift");
           }
           else if (arg.equalsIgnoreCase("all")) {
               Ananta.gui = new AnantaClientGui();
               ModuleManager.getModule(ClickGui.class).setBind(Keyboard.KEY_RSHIFT);
               AnantaMessageUtil.sendCommandMessage("gui position reset and keybind se tto Rhisft");
           }
        }
        else Ananta.gui = new AnantaClientGui();
        ModuleManager.getModule(ClickGui.class).setBind(Keyboard.KEY_RSHIFT);
    }
}
