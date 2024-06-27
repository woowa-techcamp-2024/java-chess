package com.seong.chess.pieces;

import com.seong.chess.Position;

public class Pawn extends Piece {

    private static final char REPRESENTATION = 'p';
    private static final double DEFAULT_POINT = 1.0;

    private Pawn(Color color) {
        super(Type.PAWN, color, REPRESENTATION, DEFAULT_POINT);
    }

    public static Pawn createWhite() {
        return new Pawn(Color.WHITE);
    }

    public static Pawn createBlack() {
        return new Pawn(Color.BLACK);
    }

    @Override
    public boolean isNotBlank() {
        return false;
    }

    @Override
    public Position nextPosition(String sourcePosition, Direction direction, int moveCount) {
        return null;
    }
}
