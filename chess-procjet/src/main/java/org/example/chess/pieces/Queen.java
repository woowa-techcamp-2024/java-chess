package org.example.chess.pieces;

public class Queen extends Piece{

    public Queen(Color color) {
        super(color, Type.QUEEN);
    }

    @Override
    public boolean isValidMove(String source, String destination) {
        return false;
    }
}
