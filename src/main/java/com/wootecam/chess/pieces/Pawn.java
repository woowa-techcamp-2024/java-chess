package com.wootecam.chess.pieces;

import com.wootecam.chess.Color;

public class Pawn {
    private static final Color DEFAULT_COLOR = Color.WHITE;

    private final Color color;

    public Pawn() {
        this.color = DEFAULT_COLOR;
    }

    public Pawn(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return color + " Pawn";
    }
}
