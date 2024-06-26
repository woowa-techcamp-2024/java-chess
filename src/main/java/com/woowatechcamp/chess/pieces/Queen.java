package com.woowatechcamp.chess.pieces;

public class Queen extends Piece {
    public Queen(Color color, Position position) {
        super(color, Type.QUEEN, position);
    }

    @Override
    protected void validateMove(Position newPosition) {
        int deltaX = Math.abs(getPosition().deltaX(newPosition));
        int deltaY = Math.abs(getPosition().deltaY(newPosition));

        // Queen can move vertically, horizontally, or diagonally
        if (!(deltaX == deltaY || deltaX == 0 || deltaY == 0)) {
            throw new IllegalArgumentException("Queen can only move in straight lines or diagonals.");
        }
    }
}
