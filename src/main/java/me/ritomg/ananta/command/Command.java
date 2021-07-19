package me.ritomg.ananta.command;

import me.ritomg.ananta.util.AnantaMessageUtil;

import java.util.Arrays;
import java.util.List;

public abstract class Command {

    private String name;
    private String description;
    private String[] alias;
    private String syntax;

    public Command(String name, String description, String[] alias, String syntax) {
        this.name = name;
        this.description = description;
        this.alias = alias;
        this.syntax = syntax;
    }

    public void sendWrongCommand() {
        AnantaMessageUtil.sendClientPrefixMessage("Wrong usage pls use" + syntax + "for command-" + name);
    }

    public abstract void onCommand(String[] args, String command);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

	public List<String> getAlias() {
        return Arrays.asList(alias);
    }

    public String getSyntax() {
        return syntax;
    }
}
