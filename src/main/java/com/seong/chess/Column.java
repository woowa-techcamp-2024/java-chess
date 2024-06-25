package com.seong.chess;

import com.seong.chess.pieces.Piece;

public class Column {

    private final Point point;
    private Piece piece;

    public Column(Point point) {
        this.point = point;
    }

    public Column(Point point, Piece piece) {
        this.point = point;
        this.piece = piece;
    }

    public char getRepresentation() {
        if (piece == null) {
            return '.';
        }
        return piece.getRepresentation();
    }

    public boolean hasPiece() {
        return piece != null;
    }
}
