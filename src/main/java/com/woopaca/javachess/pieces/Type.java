package com.woopaca.javachess.pieces;

public enum Type {

    PAWN('♙', '♟', 1),
    KNIGHT('♘', '♞', 2.5),
    ROOK('♖', '♜', 5),
    BISHOP('♗', '♝', 3),
    QUEEN('♕', '♛', 9),
    KING('♔', '♚', 0),
    NO_PIECE('.', '.', 0);

    private final char whiteRepresentation;
    private final char blackRepresentation;
    private final double point;

    Type(char whiteRepresentation, char blackRepresentation, double point) {
        this.whiteRepresentation = whiteRepresentation;
        this.blackRepresentation = blackRepresentation;
        this.point = point;
    }

    public char getWhiteRepresentation() {
        return whiteRepresentation;
    }

    public char getBlackRepresentation() {
        return blackRepresentation;
    }

    public double getPoint() {
        return point;
    }

}
