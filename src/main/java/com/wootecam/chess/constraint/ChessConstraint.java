package com.wootecam.chess.constraint;

import static com.wootecam.chess.error.ErrorMessage.INVALID_POSITION;

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
            throw new IllegalArgumentException(INVALID_POSITION.value);
        }
    }

    public static boolean isValidFileIndex(int y) {
        return y >= 0 && y < MAX_COL;
    }

    public static void validFileIndex(int y) {
        if (!isValidFileIndex(y)) {
            throw new IllegalArgumentException(INVALID_POSITION.value);
        }
    }
}
