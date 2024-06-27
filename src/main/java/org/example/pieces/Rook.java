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
}
