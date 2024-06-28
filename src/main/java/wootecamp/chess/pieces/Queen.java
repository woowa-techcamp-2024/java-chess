package wootecamp.chess.pieces;

import wootecamp.chess.board.MoveVector;

import java.util.Optional;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color, Type.QUEEN);
    }

    @Override
    public boolean verifyMovePosition(MoveVector moveVector) {
        Optional<Direction> direction = Direction.determineDirection(moveVector);
        return direction.filter(it -> Direction.everyDirection().contains(it)).isPresent();
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
