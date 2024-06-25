package com.wootecam.chess.pieces;

public enum Type {
    PAWN("p"),
    ROOK("r"),
    KNIGHT("n"),
    BISHOP("b"),
    QUEEN("q"),
    KING("k"),
    NO_PIECE("NO_PIECE");

    private final String representation;

    Type(String representation) {
        this.representation = representation;
    }

    public String findRepresentation(Color color) {
        if (color == Color.BLACK) {
            return representation.toUpperCase();
        }
        return representation;
    }
}
