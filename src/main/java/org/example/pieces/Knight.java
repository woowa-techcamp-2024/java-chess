package org.example.pieces;

import org.example.chess.Position;

public class Knight extends Piece {

    Knight(Color color) {
        super(color, Type.KNIGHT);
    }

    @Override
    boolean verifyMove(Position start, Position end) {
        return false;
    }
}
