package com.wootecam.chess.pieces;

import java.util.List;
import java.util.Optional;

public class Bishop extends Piece {

    private static final int MOVE_COUNT = 7;

    public Bishop(final Color color) {
        super(color, Type.BISHOP);
    }

    @Override
    public Optional<Direction> findDirection(final Position startPosition, final Position targetPosition) {
        List<Direction> directions = Direction.diagonalDirection();

        return directions.stream()
                .filter(direction -> findAnyMatchDirection(startPosition, targetPosition, direction, MOVE_COUNT))
                .findAny();
    }
}
