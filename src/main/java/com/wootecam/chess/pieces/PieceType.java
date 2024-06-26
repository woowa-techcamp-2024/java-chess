package com.wootecam.chess.pieces;

public enum PieceType {
    PAWN(1),
    KNIGHT(2.5),
    ROOK(5),
    BISHOP(3),
    QUEEN(9),
    KING(0),
    NO_PIECE(0);

    public final double point;

    PieceType(double point) {
        this.point = point;
    }
}
