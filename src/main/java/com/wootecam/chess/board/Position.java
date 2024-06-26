package com.wootecam.chess.board;

import static com.wootecam.chess.board.Board.MAX_ROW;

public class Position {
    public final int x;
    public final int y;

    public Position(String position) {
        validPositionForm(position);

        this.x = MAX_ROW - (position.charAt(1) - '0');
        this.y = position.charAt(0) - 'a';
    }

    private static void validPositionForm(String position) {
        if (position.length() != 2) {
            throw new IllegalArgumentException("Invalid position: " + position);
        }

        char row = position.charAt(1);
        if (row < '1' || row > '8') {
            throw new IllegalArgumentException("Invalid position: " + position);
        }

        char col = position.charAt(0);
        if (col < 'a' || col > 'h') {
            throw new IllegalArgumentException("Invalid position: " + position);
        }
    }
}
