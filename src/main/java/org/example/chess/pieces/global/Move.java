package org.example.chess.pieces.global;

public class Move {
    private Direction dir;
    private boolean isJumpable;

    private Move(Direction dir, boolean isJumpable) {
        this.dir = dir;
        this.isJumpable = isJumpable;
    }

    public static Move of(Direction dir, boolean isJumpable) {
        return new Move(dir, isJumpable);
    }

    public Direction getDir() {
        return dir;
    }

    public boolean isJumpable() {
        return isJumpable;
    }
}
