package org.example.pieces;

import org.example.chess.Position;

public class King extends Piece{

    King(Color color) {
        super(color, Type.KING);
    }

    @Override
    boolean verifyMove(Position start, Position end) {
        return false;
    }
}
