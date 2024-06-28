package com.wootecam.chess.constraint;

public final class ChessConstraint {
    public static final int MAX_ROW = 8;
    public static final int MAX_COL = 8;

    private ChessConstraint() {
    }

    public static boolean isValidIndex(int x, int y) {
        return x >= 0 && x < MAX_ROW && y >= 0 && y < MAX_COL;
    }

    public static void validIndex(int x, int y) {
        if (!isValidIndex(x, y)) {
            throw new IllegalArgumentException("Invalid position: " + x);
        }
    }
}
