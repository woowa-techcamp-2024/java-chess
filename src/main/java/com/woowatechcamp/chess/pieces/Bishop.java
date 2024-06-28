package com.woowatechcamp.chess.pieces;

import com.woowatechcamp.chess.game.Board;

public class Bishop extends Piece {
    public Bishop(Color color, Position position) {
        super(color, Type.BISHOP, position);
    }

    @Override
    protected void validateMove(Position newPosition, Board board) {
        if (board.isOccupiedBySameColor(newPosition, getColor())) {
            throw new IllegalArgumentException("Position occupied by same color");
        }
        int deltaX = Math.abs(newPosition.getXPos() - getPosition().getXPos());
        int deltaY = Math.abs(newPosition.getYPos() - getPosition().getYPos());
        if (deltaX != deltaY) {
            throw new IllegalArgumentException("Bishop can only move diagonally.");
        }
        if (!board.isPathClear(getPosition(), newPosition)) {
            throw new IllegalArgumentException("Path is not clear.");
        }
    }
}
