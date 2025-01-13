package com.garage.command;

public interface CommandProcessor <CommandInstance> {

    public Class<CommandInstance> getCommandType();

    public void processCommand(CommandInstance command);
}
