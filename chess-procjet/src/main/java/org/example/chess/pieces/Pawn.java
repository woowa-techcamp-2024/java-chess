package org.example.chess.pieces;

public class Pawn {

    public static final char WHITE_REPRESENTATION = 'P';
    public static final char BLACK_REPRESENTATION = 'p';

    private final Color color;
    private final char representation;

    public Color getColor() {
        return this.color;
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
