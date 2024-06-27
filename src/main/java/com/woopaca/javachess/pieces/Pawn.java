package com.woopaca.javachess.pieces;

import com.woopaca.javachess.chess.Position;

import java.util.List;

public class Pawn extends Piece {

    protected Pawn(Color color, Position position) {
        super(color, Type.PAWN, position);
    }

    @Override
    public List<Direction> getDirections() {
        return getColor() == Color.BLACK ? Direction.blackPawnDirection() : Direction.whitePawnDirection();
    }

}
