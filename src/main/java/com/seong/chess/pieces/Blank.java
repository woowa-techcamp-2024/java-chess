package com.seong.chess.pieces;

public class Blank extends Piece {

    private static final char REPRESENTATION = '.';
    private static final double DEFAULT_POINT = 0.0;

    private Blank() {
        super(Type.NO_PIECE, Color.NOCOLOR, REPRESENTATION, DEFAULT_POINT);
    }

    public static Blank create() {
        return new Blank();
    }

    @Override
    public boolean isNotBlank() {
        return true;
    }

    @Override
    public void checkPieceCanMove(Direction direction) {

    }
}
