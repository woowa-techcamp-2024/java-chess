package wootecamp.chess.pieces;

import wootecamp.chess.board.MoveVector;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color, Type.KNIGHT);
    }

    @Override
    public boolean verifyMovePosition(MoveVector moveVector) {
        Direction direction = Direction.determineDirection(moveVector);
        return Direction.knightDirection().contains(direction);
    }
}
