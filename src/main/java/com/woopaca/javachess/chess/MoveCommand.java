package com.woopaca.javachess.chess;

public class MoveCommand {

    private final String sourcePosition;
    private final String targetPosition;

    public MoveCommand(String command) {
        String[] commands = command.split(" ");
        this.sourcePosition = commands[1];
        this.targetPosition = commands[2];
    }

    public String getSourcePosition() {
        return sourcePosition;
    }

    public String getTargetPosition() {
        return targetPosition;
    }

}
