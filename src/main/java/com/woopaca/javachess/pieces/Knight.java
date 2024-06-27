package com.woopaca.javachess.pieces;

import com.woopaca.javachess.chess.Position;

import java.util.List;

public class Knight extends Piece {

    protected Knight(Color color, Position position) {
        super(color, Type.KNIGHT, position);
    }

    @Override
    public List<Direction> getDirections() {
        return Direction.knightDirection();
    }

}
