package org.example.chess.pieces;

import java.util.List;
import org.example.chess.pieces.enums.Direction;

public class Queen extends Piece{

    public Queen(Color color) {
        super(color, Type.QUEEN);
    }

    @Override
    protected List<Direction> getDirections() {
        return Direction.queenDirection();
    }
}
