package com.woopaca.javachess.chess.pieces;

public class Pawn {

    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    public static final char WHITE_PRESENTATION = '♙';
    public static final char BLACK_PRESENTATION = '♟';

    private final String color;

    public Pawn() {
        this(WHITE_COLOR);
    }

    public Pawn(final String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public char getRepresentation() {
        if (color.equals(WHITE_COLOR)) {
            return WHITE_PRESENTATION;
        }
        return BLACK_PRESENTATION;
    }
}
