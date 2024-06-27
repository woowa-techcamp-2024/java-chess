package com.seong.chess.pieces;

public class Rook extends Piece {

    private static final char REPRESENTATION = 'r';
    private static final double DEFAULT_POINT = 5.0;

    private Rook(Color color) {
        super(color, REPRESENTATION, DEFAULT_POINT);
    }

    public static Rook createWhite() {
        return new Rook(Color.WHITE);
    }

    public static Rook createBlack() {
        return new Rook(Color.BLACK);
    }

    @Override
    public boolean isNotBlank() {
        return false;
    }

    @Override
    protected boolean isPiecesDirection(Direction direction) {
        return direction.isRight();
    }
}
