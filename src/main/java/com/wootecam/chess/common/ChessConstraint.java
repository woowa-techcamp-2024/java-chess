package com.wootecam.chess.common;

public final class ChessConstraint {
    public static final int MAX_ROW = 8;
    public static final int MAX_COL = 8;

    private ChessConstraint() {
    }

    public static void validIndex(int x, int y) {
        if (x < 0 || y < 0 || x >= MAX_ROW || y >= MAX_COL) {
            throw new IllegalArgumentException("Invalid position: " + x);
        }
    }
}
