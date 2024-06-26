package com.woowatechcamp.chess.pieces;

public class Blank extends Piece {
    public Blank(Position position) {
        super(Color.NONE, Type.BLANK, position);
    }
}
