package com.woopaca.javachess.pieces;

import com.woopaca.javachess.chess.Position;

import java.util.List;

public class Blank extends Piece {

    protected Blank(Color color, Position position) {
        super(color, Type.NO_PIECE, position);
    }

    @Override
    public List<Direction> getDirections() {
        return List.of();
    }

}
