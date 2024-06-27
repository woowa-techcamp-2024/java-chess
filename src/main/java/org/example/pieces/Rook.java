package org.example.pieces;

import org.example.chess.Position;

public class Rook extends Piece{
    Rook(Color color) {
        super(color, Type.ROOK);
    }

    @Override
    boolean verifyMove(Position start, Position end) {
        return false;
    }
}
