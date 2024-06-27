package org.example.chess.pieces;

import java.util.List;
import org.example.chess.pieces.enums.Direction;

public class Bishop extends Piece{

    public Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    @Override
    protected List<Direction> getDirections() {
        return Direction.bishopDirection();
    }
}
