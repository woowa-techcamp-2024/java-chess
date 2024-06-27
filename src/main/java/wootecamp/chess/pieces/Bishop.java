package wootecamp.chess.pieces;

import wootecamp.chess.board.MoveVector;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    @Override
    public boolean verifyMovePosition(MoveVector moveVector) {
        Direction direction = Direction.determineDirection(moveVector);
        return Direction.diagonalDirection().contains(direction);
    }
}
