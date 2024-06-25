package com.seong.chess;

import com.seong.chess.pieces.Piece;

public class Column {

    private final Position position;
    private Piece piece;

    public Column(Position position) {
        this.position = position;
    }

    public Column(Position position, Piece piece) {
        this.position = position;
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
