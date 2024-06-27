package org.example.chess.pieces;

public class Blank extends Piece{

    public Blank() {
        super(Color.NONCOLOR, Type.NO_TYPE);
    }

    @Override
    public boolean isValidMove(String source, String destination) {
        return false;
    }
}
