package org.example.pieces;

import org.example.chess.Direction;
import org.example.chess.Position;

public class NoPiece extends Piece {

    NoPiece(Color color) {
        super(color, Type.NO_PIECE);
    }

    @Override
    public boolean verifyMove(Position start, Position end) {
        return false;
    }

    @Override
    public boolean verifyRecursive(Position now, Position dest, Direction direction) {
        return false;
    }
}
