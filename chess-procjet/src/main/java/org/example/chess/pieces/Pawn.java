package org.example.chess.pieces;

public class Pawn extends Piece{

    public Pawn(Color color) {
        super(color, Type.PAWN);
    }

    @Override
    public boolean isValidMove(String source, String destination) {
        return false;
    }
}
