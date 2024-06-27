package wootecamp.chess.pieces;

import wootecamp.chess.board.MoveVector;

import java.util.Optional;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color, Type.KNIGHT);
    }

    @Override
    public boolean verifyMovePosition(MoveVector moveVector) {
        Optional<Direction> direction = Direction.determineDirection(moveVector);
        return direction.filter(it -> Direction.knightDirection().contains(it)).isPresent();
    }
}
