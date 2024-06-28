package com.woopaca.javachess.pieces;

import java.util.List;

public class Bishop extends Piece {

    protected Bishop(Color color) {
        super(color, Type.BISHOP, Range.INFINITY);
    }

    @Override
    public List<Direction> getDirections() {
        return Direction.diagonalDirection();
    }

}
