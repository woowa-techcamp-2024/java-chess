package com.woowatechcamp.chess.pieces;

import com.woowatechcamp.chess.game.Board;

public class King extends Piece {
    public King(Color color, Position position) {
        super(color, Type.KING, position);
    }

    @Override
    protected void validateMove(Position position, Board board) {
        if (board.isOccupiedBySameColor(position, getColor())) {
            throw new IllegalArgumentException("Position occupied by same color");
        }
        Direction direction = Direction.fromPositions(getPosition(), position);
        if (!Direction.everyDirection().contains(direction)) {
            throw new IllegalArgumentException("King can only move one square in any direction.");
        }
    }
}
