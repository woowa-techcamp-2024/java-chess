package com.seong.chess;

import com.seong.chess.pieces.Pawn;
import com.seong.chess.pieces.Piece;

public class Column {

    private final Point point;
    private Piece piece;

    public Column(Point point) {
        this.point = point;
    }

    public Column(Point point, Piece piece) {
        this(point);
        this.piece = piece;
    }

    public boolean isBlackPawnLine() {
        return point.r() == 1;
    }

    public boolean isWhitePawnLine() {
        return point.r() == 6;
    }

    public void initialize(Piece piece) {
        this.piece = piece;
    }

    public char getPawnRepresentation() {
        return ((Pawn) piece).getRepresentation();
    }
}
