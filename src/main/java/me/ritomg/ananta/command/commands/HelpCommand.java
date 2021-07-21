package me.ritomg.ananta.command.commands;

import me.ritomg.ananta.command.Command;
import me.ritomg.ananta.command.CommandManager;
import me.ritomg.ananta.util.misc.AnantaMessageUtil;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("Help", "Get list of all modules and thier descritpiton", new String[]{"help", "commandlist", "commands"}, "help '/' help syntax");
    }

    @Override
    public void onCommand(String[] args, String command) {

        for (Command c : CommandManager.commandList) {
            AnantaMessageUtil.sendClientPrefixMessage(c.getName() + ": " + "\"" + c.getDescription() + "\"!");
        }

        if (args.length == 1) {
           if (args[0].equalsIgnoreCase("syntax")) {
                for (Command c : CommandManager.commandList) {
                    AnantaMessageUtil.sendClientPrefixMessage(c.getName() + ": " + "\"" + c.getSyntax() + "\"!");
                }
            }
        }
    }
}
