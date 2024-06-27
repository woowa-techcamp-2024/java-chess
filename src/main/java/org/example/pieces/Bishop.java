package org.example.pieces;

import org.example.chess.Direction;
import org.example.chess.Position;

public class Bishop extends Piece{
    Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    @Override
    public boolean verifyMove(Position start, Position end) {
        Direction direction = Direction.determineDirection(start, end);
        return Direction.diagonalDirection().contains(direction);
    }

    @Override
    public boolean verifyRecursive(Position now, Position dest, Direction direction) {
        return false;
    }
}
