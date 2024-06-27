package com.seong.chess.pieces;

public class Queen extends Piece {

    private static final char REPRESENTATION = 'q';
    private static final double DEFAULT_POINT = 9.0;

    private Queen(Color color) {
        super(color, REPRESENTATION, DEFAULT_POINT);
    }

    public static Queen createBlack() {
        return new Queen(Color.BLACK);
    }

    public static Queen createWhite() {
        return new Queen(Color.WHITE);
    }

    @Override
    public boolean isNotBlank() {
        return true;
    }

    @Override
    protected boolean isPiecesDirection(Direction direction) {
        return direction.isDiagonal() || direction.isRight();
    }
}
