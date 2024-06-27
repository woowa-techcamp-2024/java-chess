package com.wootecam.chess.pieces;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class King extends Piece {

    private static final int MOVE_COUNT = 1;

    public King(final Color color) {
        super(color, Type.KING);
    }

    @Override
    public Optional<Direction> findDirection(final Position startPosition, final Position targetPosition) {
        List<Direction> directions = Direction.everyDirection();

        return directions.stream()
                .filter(direction -> findAnyMatchDirection(startPosition, targetPosition, direction))
                .findAny();
    }

    private boolean findAnyMatchDirection(final Position startPosition, final Position targetPosition,
                                          final Direction direction) {
        return IntStream.rangeClosed(1, MOVE_COUNT)
                .mapToObj(i -> startPosition.addPosition(direction.getRow() * MOVE_COUNT,
                        direction.getColumn() * MOVE_COUNT))
                .anyMatch(nextPosition -> nextPosition.equals(targetPosition));
    }
}
