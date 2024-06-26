package com.woowatechcamp.chess.pieces;

public class Pawn extends Piece {
    public Pawn(Color color, Position position) {
        super(color, Type.PAWN, position);
    }

    @Override
    protected void validateMove(Position newPosition) {
        int deltaX = getPosition().deltaX(newPosition);
        int deltaY = getPosition().deltaY(newPosition);

        if (deltaX != 0 || deltaY != 1) {
            throw new IllegalArgumentException("Pawn can only move forward one square.");
        }
    }
}
