package org.example.pieces;

import org.example.chess.Direction;
import org.example.chess.Position;

public class Knight extends Piece {

    Knight(Color color) {
        super(color, Type.KNIGHT);
    }

    @Override
    public boolean verifyMove(Position start, Position end) {
        Direction direction = Direction.determineDirection(start, end);
        return Direction.knightDirection().contains(direction);
    }

    @Override
    public boolean verifyRecursive(Position now, Position dest, Direction direction) {
        return false;
    }
}
