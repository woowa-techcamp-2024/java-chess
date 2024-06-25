package org.example.chess.pieces;

public enum PieceName {
    PAWN("P"),
    KNIGHT("K"),
    ROOK("R"),
    BISHOP("B"),
    QUEEN("Q"),
    KING("K");

    private final String representation;

    public String getRepresentation() {
        return this.representation;
    }

    PieceName(String representation) {
        this.representation = representation;
    }
}
