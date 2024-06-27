package org.example.chess.pieces;

import java.util.List;
import org.example.chess.pieces.enums.Direction;

public class Pawn extends Piece{

    //TODO: move시 false로 변환 필요.
    private boolean isFirstMove = true;

    public Pawn(Color color) {
        super(color, Type.PAWN);
    }

    @Override
    protected double getMaxDistance() {
        return isFirstMove ? 2 : 1;
    }

    @Override
    protected List<Direction> getDirections() {
        return this.getColor() == Color.BLACK ? Direction.blackPawnDirection() : Direction.whitePawnDirection();
    }
}
