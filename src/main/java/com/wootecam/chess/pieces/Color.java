package com.wootecam.chess.pieces;

public enum Color {
    WHITE("white"),
    BLACK("black");

    public final String displayName;

    Color(String displayName) {
        this.displayName = displayName;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }
}
