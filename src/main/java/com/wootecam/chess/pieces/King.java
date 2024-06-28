package com.wootecam.chess.pieces;

import java.util.List;
import java.util.Optional;

public class King extends Piece {

    private static final int MOVE_COUNT = 1;

    public King(final Color color) {
        super(color, Type.KING);
    }

    @Override
    public Optional<Direction> findDirection(final Position startPosition, final Position targetPosition) {
        List<Direction> directions = Direction.everyDirection();

        return directions.stream()
                .filter(direction -> findAnyMatchDirection(startPosition, targetPosition, direction, MOVE_COUNT))
                .findAny();
    }
}
