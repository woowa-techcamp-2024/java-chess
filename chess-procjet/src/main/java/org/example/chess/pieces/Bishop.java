package org.example.chess.pieces;

public class Bishop extends Piece{

    public Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    @Override
    public boolean isValidMove(String source, String destination) {
        return false;
    }
}
