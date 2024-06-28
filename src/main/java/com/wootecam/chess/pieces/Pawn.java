package com.wootecam.chess.pieces;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class Pawn extends Piece {

    private static final int BLACK_PAWN_INIT_ROW = 1;
    private static final int BLACK_PAWN_DOUBLE_JUMP_ROW = 3;
    private static final int WHITE_PAWN_DOUBLE_JUMP_ROW = 4;
    private static final int WHITE_PAWN_INIT_ROW = 6;
    private static final int MOVE_COUNT = 1;

    public Pawn(final Color color) {
        super(color, Type.PAWN);
    }

    @Override
    public Optional<Direction> findDirection(final Position startPosition, final Position targetPosition) {
        if (isFirstMoveWithTwoJump(startPosition, targetPosition)) {
            return Optional.of(Direction.getPawnFirstMoveDirection(getColor()));
        }
        if (isBlack()) {
            List<Direction> directions = Direction.blackPawnDirection();
            return directions.stream()
                    .filter(direction -> findAnyMatchDirection(startPosition, targetPosition, direction))
                    .findAny();
        }
        List<Direction> directions = Direction.whitePawnDirection();
        return directions.stream()
                .filter(direction -> findAnyMatchDirection(startPosition, targetPosition, direction))
                .findAny();
    }

    private boolean isFirstMoveWithTwoJump(final Position startPosition, final Position targetPosition) {
        return (startPosition.row() == BLACK_PAWN_INIT_ROW && targetPosition.row() == BLACK_PAWN_DOUBLE_JUMP_ROW
                && startPosition.column() == targetPosition.column())
                || (startPosition.row() == WHITE_PAWN_INIT_ROW && targetPosition.row() == WHITE_PAWN_DOUBLE_JUMP_ROW
                && startPosition.column() == targetPosition.column());
    }

    private boolean findAnyMatchDirection(final Position startPosition, final Position targetPosition,
                                          final Direction direction) {
        return IntStream.range(0, MOVE_COUNT)
                .mapToObj(i -> startPosition.addPosition(direction.getRow(), direction.getColumn()))
                .anyMatch(nextPosition -> nextPosition.equals(targetPosition));
    }
}
