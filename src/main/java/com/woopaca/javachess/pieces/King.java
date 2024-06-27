package com.woopaca.javachess.pieces;

import com.woopaca.javachess.chess.Position;

import java.util.List;

public class King extends Piece {

    protected King(Color color, Position position) {
        super(color, Type.KING, position);
    }

    @Override
    public List<Direction> getDirections() {
        return Direction.everyDirection();
    }

}
