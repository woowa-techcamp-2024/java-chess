package com.seong.chess.pieces;

import com.seong.chess.Position;

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
    public Position nextPosition(String sourcePosition, Direction direction, int moveCount) {
        return null;
    }
}
