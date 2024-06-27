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
}
