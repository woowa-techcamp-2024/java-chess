package com.wootecam.chess.pieces;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class Bishop extends Piece {

    private static final int MOVE_COUNT = 7;

    public Bishop(final Color color) {
        super(color, Type.BISHOP);
    }

    @Override
    public Optional<Direction> findDirection(final Position startPosition, final Position targetPosition) {
        List<Direction> directions = Direction.diagonalDirection();

        return directions.stream()
                .filter(direction -> findAnyMatchDirection(startPosition, targetPosition, direction))
                .findAny();
    }

    private boolean findAnyMatchDirection(final Position startPosition, final Position targetPosition,
                                          final Direction direction) {
        return IntStream.rangeClosed(1, MOVE_COUNT)
                .mapToObj(count -> startPosition.addPosition(direction.getRow() * count, direction.getColumn() * count))
                .anyMatch(nextPosition -> nextPosition.equals(targetPosition));
    }
}
