package com.woopaca.javachess.pieces;

import java.util.List;

public class Pawn extends Piece {

    protected Pawn(Color color) {
        super(color, Type.PAWN, Range.ONE);
    }

    @Override
    public List<Direction> getDirections() {
        return isBlack() ? Direction.blackPawnDirection() : Direction.whitePawnDirection();
    }

}
