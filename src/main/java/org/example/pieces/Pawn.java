package org.example.pieces;

import static org.example.chess.ChessConst.BLACK_PAWN_START_ROW;
import static org.example.chess.ChessConst.WHITE_PAWN_START_ROW;

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
            return depth == 1 || (depth == 2 && start.getRow() == BLACK_PAWN_START_ROW);
        } else if (Direction.whitePawnDirection().contains(direction) && isWhite()) {
            return depth == 1 || (depth == 2 && start.getRow() == WHITE_PAWN_START_ROW);
        }
        return false;
    }
}
