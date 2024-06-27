package com.seong.chess.pieces;

public class Rook extends Piece {

    private static final char REPRESENTATION = 'r';
    private static final double DEFAULT_POINT = 5.0;

    private Rook(Color color) {
        super(Type.ROOK, color, REPRESENTATION, DEFAULT_POINT);
    }

    public static Piece createWhite() {
        return new Rook(Color.WHITE);
    }

    public static Piece createBlack() {
        return new Rook(Color.BLACK);
    }

    @Override
    public boolean isNotBlank() {
        return false;
    }

    @Override
    public void checkPieceCanMove(Direction direction) {

    }
}
