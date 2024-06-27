package org.example.chess.pieces;

import java.util.List;
import org.example.chess.pieces.enums.Direction;

public class Rook extends Piece{

    public Rook(Color color) {
        super(color, Type.ROOK);
    }

    @Override
    protected List<Direction> getDirections() {
        return Direction.rookDirection();
    }
}
