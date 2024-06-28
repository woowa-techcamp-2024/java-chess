package com.wootecam.chess.pieces.property;

public enum Color {
    WHITE("White"),
    BLACK("Black"),
    NO_COLOR("None");

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

    public boolean isNoColor() {
        return this == NO_COLOR;
    }

    public Color toggle() {
        if (this == WHITE) {
            return BLACK;
        }

        return WHITE;
    }
}
