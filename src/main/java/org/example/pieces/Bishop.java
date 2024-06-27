package org.example.pieces;

import org.example.chess.Position;

public class Bishop extends Piece{
    Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    @Override
    boolean verifyMove(Position start, Position end) {
        return false;
    }
}
