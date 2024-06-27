package org.example.pieces;

import org.example.chess.Direction;
import org.example.chess.Position;

public class Queen extends Piece{

    Queen(Color color) {
        super(color, Type.QUEEN);
    }

    @Override
    public boolean verifyMove(Position start, Position end) {
        Direction direction = Direction.determineDirection(start, end);
        return Direction.everyDirection().contains(direction);
    }

    @Override
    public boolean verifyRecursive(Position now, Position dest, Direction direction) {
        if (now.equals(dest)) {
            return true;
        }

        Position next = now.next(direction);
        return verifyRecursive(next, dest, direction);
    }
}
