package com.wootecam.chess.pieces;

public enum Color {
    WHITE,
    BLACK,
    ;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }
}
