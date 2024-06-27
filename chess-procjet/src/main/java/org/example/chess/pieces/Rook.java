package org.example.chess.pieces;

public class Rook extends Piece{

    public Rook(Color color) {
        super(color, Type.ROOK);
    }

    @Override
    public boolean isValidMove(String source, String destination) {
        return false;
    }
}
