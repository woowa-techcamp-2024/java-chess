package com.woowatechcamp.chess.pieces;

public class Bishop extends Piece {
    public Bishop(Color color, Position position) {
        super(color, Type.BISHOP, position);
    }

    @Override
    protected void validateMove(Position newPosition) {
        int deltaX = Math.abs(getPosition().deltaX(newPosition));
        int deltaY = Math.abs(getPosition().deltaY(newPosition));

        if (deltaX != deltaY) {
            throw new IllegalArgumentException("Bishop can only move diagonally.");
        }
    }
}
