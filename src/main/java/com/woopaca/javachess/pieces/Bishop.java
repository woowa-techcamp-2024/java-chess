package com.woopaca.javachess.pieces;

import com.woopaca.javachess.chess.Position;

import java.util.List;

public class Bishop extends Piece {

    protected Bishop(Color color, Position position) {
        super(color, Type.BISHOP, position);
    }

    @Override
    public List<Direction> getDirections() {
        return Direction.diagonalDirection();
    }

}
