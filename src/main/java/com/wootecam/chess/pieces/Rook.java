package com.wootecam.chess.pieces;

import java.util.List;
import java.util.Optional;

public class Rook extends Piece {

    private static final int MOVE_COUNT = 7;

    public Rook(final Color color) {
        super(color, Type.ROOK);
    }

    @Override
    public Optional<Direction> findDirection(final Position startPosition, final Position targetPosition) {
        List<Direction> directions = Direction.linearDirection();

        return directions.stream()
                .filter(direction -> findAnyMatchDirection(startPosition, targetPosition, direction, MOVE_COUNT))
                .findAny();
    }
}
