package com.woopaca.javachess.pieces;

import java.util.List;

public class Blank extends Piece {

    protected Blank(Color color) {
        super(color, Type.NO_PIECE, Range.ZERO);
    }

    @Override
    public List<Direction> getDirections() {
        return List.of();
    }

}
