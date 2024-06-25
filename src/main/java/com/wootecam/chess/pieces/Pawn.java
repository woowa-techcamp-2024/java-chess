package com.wootecam.chess.pieces;

public class Pawn {
    private static final Color DEFAULT_COLOR = Color.WHITE;

    private final Color color;
    private final PieceRepresentation representation;

    public Pawn() {
        this.color = DEFAULT_COLOR;
        this.representation = this.color.getRepresentation();
    }

    public Pawn(Color color) {
        this.color = color;
        this.representation = this.color.getRepresentation();
    }

    public Color getColor() {
        return color;
    }

    public PieceRepresentation getRepresentation() {
        return representation;
    }

    @Override
    public String toString() {
        return color + " Pawn";
    }
}
