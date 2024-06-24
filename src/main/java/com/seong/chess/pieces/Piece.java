package com.seong.chess.pieces;

public abstract class Piece {
    public static final String BLACK_COLOR = "black";
    public static final String WHITE_COLOR = "white";

    private final String color;

    public Piece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
