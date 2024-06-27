package com.woowatechcamp.chess.pieces;

import com.woowatechcamp.chess.Board;

public class Blank extends Piece {
    public Blank(Position position) {
        super(Color.NONE, Type.BLANK, position);
    }

    @Override
    protected void validateMove(Position position, Board board) {
    }
}
