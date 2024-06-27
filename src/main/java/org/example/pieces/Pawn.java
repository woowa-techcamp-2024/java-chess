package org.example.pieces;

import org.example.chess.Direction;
import org.example.chess.Position;

public class Pawn extends Piece {

    Pawn(Color color) {
        super(color, Type.PAWN);
    }

    @Override
    public boolean verifyMove(Position start, Position end) {
        Direction direction = Direction.determineDirection(start, end);
        int depth = Math.abs(start.getRow() - end.getRow());

        if (Direction.blackPawnDirection().contains(direction) && isBlack()) {
            return depth == 1 || (depth == 2 && start.getRow() == 7);
        } else if (Direction.whitePawnDirection().contains(direction) && isWhite()) {
            return depth == 1 || (depth == 2 && start.getRow() == 2);
        }
        return false;
    }
}
