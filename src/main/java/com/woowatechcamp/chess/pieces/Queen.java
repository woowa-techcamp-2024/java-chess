package com.woowatechcamp.chess.pieces;

import com.woowatechcamp.chess.Board;

public class Queen extends Piece {
    public Queen(Color color, Position position) {
        super(color, Type.QUEEN, position);
    }

    @Override
    protected void validateMove(Position newPosition, Board board) {
        if (board.isOccupiedBySameColor(newPosition, getColor())) {
            throw new IllegalArgumentException("Position occupied by same color");
        }
        int deltaX = Math.abs(newPosition.getXPos() - getPosition().getXPos());
        int deltaY = Math.abs(newPosition.getYPos() - getPosition().getYPos());
        if (deltaX != deltaY && deltaX != 0 && deltaY != 0) {
            throw new IllegalArgumentException("Queen can only move in straight lines or diagonals.");
        }
        if (!board.isPathClear(getPosition(), newPosition)) {
            throw new IllegalArgumentException("Path is not clear.");
        }
    }
}
