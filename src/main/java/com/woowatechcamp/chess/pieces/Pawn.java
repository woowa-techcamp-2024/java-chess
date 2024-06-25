package com.woowatechcamp.chess.pieces;

public class Pawn {
    public static final char WHITE_REPRESENTATION = '♙';
    public static final char BLACK_REPRESENTATION = '♟';
    private final Color color;

    public Pawn() {
        this.color = Color.WHITE;
    }

    public Pawn(Color color) {
        this.color = color;
    }

    public Pawn(String color) {
        this.color = Color.fromString(color);
    }

    public Color getColor() {
        return color;
    }

    public char getRepresentation() {
        if (color == Color.WHITE) {
            return WHITE_REPRESENTATION;
        }
        return BLACK_REPRESENTATION;
    }

    @Override
    public String toString() {
        return String.valueOf(getRepresentation());
    }
}
