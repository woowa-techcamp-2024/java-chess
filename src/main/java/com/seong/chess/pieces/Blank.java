package com.seong.chess.pieces;

public class Blank extends Piece {

    private static final char REPRESENTATION = '.';
    private static final double DEFAULT_POINT = 0.0;

    private Blank() {
        super(Color.NOCOLOR, REPRESENTATION, DEFAULT_POINT);
    }

    public static Blank create() {
        return new Blank();
    }

    @Override
    public boolean isNotBlank() {
        return false;
    }

    @Override
    public boolean isPiecesDirection(Direction direction) {
        return false;
    }
}
