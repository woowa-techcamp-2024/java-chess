package org.example.chess.pieces;

import java.util.List;
import org.example.chess.pieces.enums.Direction;
import org.example.utils.MathUtils;

public class King extends Piece {

    public King(Color color) {
        super(color, Type.KING);
    }

    @Override
    protected double getMaxDistance() {
        return MathUtils.getDistance(0.0, 0.0, 1.0, 1.0);
    }

    @Override
    protected List<Direction> getDirections() {
        return Direction.kingDirection();
    }
}
