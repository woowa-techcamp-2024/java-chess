package com.wootecam.chess.pieces;

import java.util.Optional;

public class Blank extends Piece {

    public Blank() {
        super(Color.NO_COLOR, Type.NO_PIECE);
    }

    @Override
    public Optional<Direction> findDirection(final Position startPosition, final Position targetPosition) {
        throw new IllegalArgumentException("빈칸은 방향이 없습니다.");
    }
}
