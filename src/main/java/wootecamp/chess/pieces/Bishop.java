package wootecamp.chess.pieces;

import wootecamp.chess.board.MoveVector;

import java.util.Optional;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    @Override
    public boolean verifyMovePosition(MoveVector moveVector) {
        Optional<Direction> direction = Direction.determineDirection(moveVector);
        return direction.filter(it -> Direction.diagonalDirection().contains(it)).isPresent();
    }
}
