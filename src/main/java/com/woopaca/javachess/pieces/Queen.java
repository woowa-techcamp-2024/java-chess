package com.woopaca.javachess.pieces;

import com.woopaca.javachess.chess.Position;

import java.util.List;

public class Queen extends Piece {

    protected Queen(Color color, Position position) {
        super(color, Type.QUEEN, position);
    }

    @Override
    public List<Direction> getDirections() {
        return Direction.everyDirection();
    }

}
