package com.woopaca.javachess.chess.pieces;

public enum PieceType {

    PAWN('♙', '♟'),
    KNIGHT('♘', '♞'),
    ROOK('♖', '♜'),
    BISHOP('♗', '♝'),
    QUEEN('♕', '♛'),
    KING('♔', '♚');

    private final char whiteRepresentation;
    private final char blackRepresentation;

    PieceType(char whiteRepresentation, char blackRepresentation) {
        this.whiteRepresentation = whiteRepresentation;
        this.blackRepresentation = blackRepresentation;
    }

    public char getWhiteRepresentation() {
        return whiteRepresentation;
    }

    public char getBlackRepresentation() {
        return blackRepresentation;
    }

}
