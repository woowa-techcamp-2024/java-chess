package org.example.pieces;

import org.example.chess.Position;

public class Queen extends Piece{

    Queen(Color color) {
        super(color, Type.QUEEN);
    }

    @Override
    boolean verifyMove(Position start, Position end) {
        return false;
    }
}
