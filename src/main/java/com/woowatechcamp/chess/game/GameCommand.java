package com.woowatechcamp.chess.game;

import java.util.Arrays;

public enum GameCommand {
    START("start"),
    END("end"),
    MOVE("move");

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static GameCommand fromString(String command) {
        if (command == null || command.isEmpty()) {
            throw new IllegalArgumentException("Command cannot be null or empty");
        }
        return Arrays.stream(GameCommand.values())
                .filter(value -> value.command.equalsIgnoreCase(command.split(" ")[0]))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such command: " + command));
    }
}
