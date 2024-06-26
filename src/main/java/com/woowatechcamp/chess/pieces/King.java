package com.woowatechcamp.chess.pieces;

public class King extends Piece {
    public King(Color color, Position position) {
        super(color, Type.KING, position);
    }

    @Override
    protected void validateMove(Position position) {
        int deltaX = getPosition().deltaX(position);
        int deltaY = getPosition().deltaY(position);

        if (deltaX > 1 || deltaY > 1) {
            throw new IllegalArgumentException("King can only move one square in any direction.");
        }
    }
}
