package com.woopaca.javachess.pieces;

import com.woopaca.javachess.chess.Position;

import java.util.List;

public class Rook extends Piece {

    protected Rook(Color color, Position position) {
        super(color, Type.ROOK, position);
    }

    @Override
    public List<Direction> getDirections() {
        return Direction.linearDirection();
    }

}
