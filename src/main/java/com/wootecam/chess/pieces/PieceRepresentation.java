package com.wootecam.chess.pieces;

public enum PieceRepresentation {
    BLACK_PAWN("P"),
    WHITE_PAWN("p"),
    NONE(".");

    public final String value;

    PieceRepresentation(String value) {
        this.value = value;
    }
}
