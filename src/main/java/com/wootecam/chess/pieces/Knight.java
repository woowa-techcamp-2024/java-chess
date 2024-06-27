package com.wootecam.chess.pieces;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class Knight extends Piece {

    private final static int MOVE_COUNT = 1;

    public Knight(final Color color) {
        super(color, Type.KNIGHT);
    }

    @Override
    public Optional<Direction> findDirection(final Position startPosition, final Position targetPosition) {
        List<Direction> directions = Direction.knightDirection();

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
