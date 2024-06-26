package com.woowatechcamp.chess.pieces;

public class Knight extends Piece {
    public Knight(Color color, Position position) {
        super(color, Type.KNIGHT, position);
    }

    @Override
    protected void validateMove(Position newPosition) {
        int deltaX = getPosition().deltaX(newPosition);
        int deltaY = getPosition().deltaY(newPosition);

        if (!((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2))) {
            throw new IllegalArgumentException("Knight can only move in an L shape.");
        }
    }
}
