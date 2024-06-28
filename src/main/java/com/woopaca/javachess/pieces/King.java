package com.woopaca.javachess.pieces;

import java.util.List;

public class King extends Piece {

    protected King(Color color) {
        super(color, Type.KING, Range.ONE);
    }

    @Override
    public List<Direction> getDirections() {
        return Direction.everyDirection();
    }

}
