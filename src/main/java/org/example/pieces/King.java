package org.example.pieces;

import org.example.chess.Direction;
import org.example.chess.Position;

public class King extends Piece{

    King(Color color) {
        super(color, Type.KING);
    }

    @Override
    public boolean verifyMove(Position start, Position end) {
        Direction direction = Direction.determineDirection(start, end);
        int depth = Direction.depth(start, end);
        return Direction.everyDirection().contains(direction) && depth == 1;
    }
}
