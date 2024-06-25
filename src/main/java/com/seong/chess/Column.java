package com.seong.chess;

import com.seong.chess.pieces.Pawn;
import com.seong.chess.pieces.Piece;

public class Column {

    private final Point point;
    private Piece piece;

    public Column(Point point) {
        this.point = point;
    }

    public boolean isSameRow(int row) {
        return point.r() == row;
    }

    public void initialize(Piece piece) {
        this.piece = piece;
    }

    public char getPawnRepresentation() {
        return ((Pawn) piece).getRepresentation();
    }
}
