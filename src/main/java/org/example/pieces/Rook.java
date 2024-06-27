package org.example.pieces;

import org.example.chess.Direction;
import org.example.chess.Position;

public class Rook extends Piece{
    Rook(Color color) {
        super(color, Type.ROOK);
    }

    @Override
    public boolean verifyMove(Position start, Position end) {
        Direction direction = Direction.determineDirection(start, end);
        return Direction.linearDirection().contains(direction);
    }

    @Override
     public boolean verifyRecursive(Position now, Position dest, Direction direction) {
        if(now == dest) {
            return true;
        }

        Position next = now.next(direction);
        if(next == null) {
            return false;
        }

        return verifyRecursive(next, dest, direction);
    }
}
