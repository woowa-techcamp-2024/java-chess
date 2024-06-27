package com.woopaca.javachess.pieces;

import com.woopaca.javachess.chess.Position;

public class Pawn extends Piece {

    protected Pawn(Color color, Position position) {
        super(color, Type.PAWN, position);
    }

}
