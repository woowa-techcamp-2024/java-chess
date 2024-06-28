package com.wootecam.chess.pieces;

import java.util.List;
import java.util.Optional;

public class Knight extends Piece {

    private final static int MOVE_COUNT = 1;

    public Knight(final Color color) {
        super(color, Type.KNIGHT);
    }

    @Override
    public Optional<Direction> findDirection(final Position startPosition, final Position targetPosition) {
        List<Direction> directions = Direction.knightDirection();

        return directions.stream()
                .filter(direction -> findAnyMatchDirection(startPosition, targetPosition, direction, MOVE_COUNT))
                .findAny();
    }
}
