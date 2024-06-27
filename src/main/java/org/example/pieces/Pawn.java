package org.example.pieces;

import org.example.chess.Position;

public class Pawn extends Piece {

    Pawn(Color color) {
        super(color, Type.PAWN);
    }

    @Override
    boolean verifyMove(Position start, Position end) {
        return false;
    }
}
