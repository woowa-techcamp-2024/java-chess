package org.example.chess.pieces;

public class Pawn {

    public static final char WHITE_REPRESENTATION = 'p';
    public static final char BLACK_REPRESENTATION = 'P';

    private final Color color;
    private final char representation;

    public Color getColor() {
        return this.color;
    }

    public char getRepresentation() {
        return this.representation;
    }

    public Pawn() {
        this.color = Color.WHITE;
        this.representation = WHITE_REPRESENTATION;
    }

    public Pawn(Color color, char representation) {
        this.color = color;
        this.representation = representation;
    }
}
