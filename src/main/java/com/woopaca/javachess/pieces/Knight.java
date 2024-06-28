package com.woopaca.javachess.pieces;

import java.util.List;

public class Knight extends Piece {

    protected Knight(Color color) {
        super(color, Type.KNIGHT, Range.ONE);
    }

    @Override
    public List<Direction> getDirections() {
        return Direction.knightDirection();
    }

}
