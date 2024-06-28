package wootecamp.chess.pieces;

import wootecamp.chess.board.MoveVector;

import java.util.Optional;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color, Type.ROOK);
    }

    @Override
    public boolean verifyMovePosition(MoveVector moveVector) {
        Optional<Direction> direction = Direction.determineDirection(moveVector);
        return direction.filter(it -> Direction.linearDirection().contains(it)).isPresent();
    }

    @Override
    public boolean canJump() {
        return false;
    }
}