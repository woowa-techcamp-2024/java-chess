package com.woopaca.javachess.pieces;

import com.woopaca.javachess.chess.Position;

public class Blank extends Piece {

    protected Blank(Color color, Position position) {
        super(color, Type.NO_PIECE, position);
    }

}
