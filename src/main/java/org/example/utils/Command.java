package org.example.utils;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    INVALID("invalid");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command fromString(String command) {
        for (Command cmd : Command.values()) {
            if (cmd.command.equalsIgnoreCase(command)) {
                return cmd;
            }
        }
        return INVALID;
    }
}