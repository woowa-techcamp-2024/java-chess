package com.seong.chess.pieces;

public class King extends Piece {

    private static final char REPRESENTATION = 'k';
    private static final double DEFAULT_POINT = 0.0;

    private King(Color color) {
        super(Type.KING, color, REPRESENTATION, DEFAULT_POINT);
    }

    public static King createWhite() {
        return new King(Color.WHITE);
    }

    public static King createBlack() {
        return new King(Color.BLACK);
    }

    @Override
    public boolean isNotBlank() {
        return false;
    }
}
