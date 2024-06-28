package com.woowatechcamp.chess.pieces;

import com.woowatechcamp.chess.game.Board;

public class Knight extends Piece {
    public Knight(Color color, Position position) {
        super(color, Type.KNIGHT, position);
    }

    @Override
    protected void validateMove(Position newPosition, Board board) {
        if (board.isOccupiedBySameColor(newPosition, getColor())) {
            throw new IllegalArgumentException("Position occupied by same color");
        }
        Direction direction = Direction.fromPositions(getPosition(), newPosition);
        if (!Direction.knightDirection().contains(direction)) {
            throw new IllegalArgumentException("Knight can only move in an L shape.");
        }
    }
}
