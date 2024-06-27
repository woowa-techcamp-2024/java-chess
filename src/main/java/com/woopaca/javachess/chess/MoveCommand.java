package com.woopaca.javachess.chess;

public class MoveCommand {

    private final String sourceFileRank;
    private final String targetFileRank;

    public MoveCommand(String command) {
        String[] commands = command.split(" ");
        this.sourceFileRank = commands[1];
        this.targetFileRank = commands[2];
    }

    public Position getSourcePosition() {
        return new Position(sourceFileRank);
    }

    public Position getTargetPosition() {
        return new Position(targetFileRank);
    }

}
