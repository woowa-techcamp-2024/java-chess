package com.woopaca.javachess.pieces;

import java.util.List;

public class Rook extends Piece {

    protected Rook(Color color) {
        super(color, Type.ROOK);
    }

    @Override
    public List<Direction> getDirections() {
        return Direction.linearDirection();
    }

}
