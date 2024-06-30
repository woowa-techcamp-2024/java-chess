package wootecamp.chess.pieces;

import wootecamp.chess.board.Direction;
import wootecamp.chess.board.MoveVector;

import java.util.List;

public class Pawn extends Piece {
    private static final List<Integer> VALID_SQUARE_DISTANCES = List.of(1, 2, 4);

    public Pawn(Color color) {
        super(color, Type.PAWN);
    }

    @Override
    public boolean verifyMovePosition(MoveVector moveVector) {
        final Direction direction = moveVector.findDirection().orElseThrow(
                () -> new IllegalArgumentException("정의되지 않은 방향입니다.")
        );

        final List<Direction> validDirections = color == Color.WHITE ?
                Direction.whitePawnDirection() : Direction.blackPawnDirection();

        return validDirections.contains(direction)
                && VALID_SQUARE_DISTANCES.contains(moveVector.calculateSquareDistance());
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
