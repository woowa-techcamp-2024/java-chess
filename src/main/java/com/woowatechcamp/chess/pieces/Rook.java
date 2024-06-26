package com.woowatechcamp.chess.pieces;

public class Rook extends Piece {
    public Rook(Color color, Position position) {
        super(color, Type.ROOK, position);
    }

    @Override
    protected void validateMove(Position newPosition) {
        int deltaX = getPosition().deltaX(newPosition);
        int deltaY = getPosition().deltaY(newPosition);

        if (deltaX != 0 && deltaY != 0) {
            throw new IllegalArgumentException("Rook can only move in straight lines.");
        }
    }
}
