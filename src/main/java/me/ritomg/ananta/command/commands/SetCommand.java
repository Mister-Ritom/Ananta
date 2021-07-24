package me.ritomg.ananta.command.commands;

import me.ritomg.ananta.command.Command;

public class SetCommand extends Command {
//Doesn't work currently
    public SetCommand() {
        super("Set", "Sets value of a setting. types - string,int,double,mode", new String[]{"set", "setSetting"}, "Set {module} {type} {setting} {value}");
    }

    @Override
    public void onCommand(String[] args, String command) {

    }
}
