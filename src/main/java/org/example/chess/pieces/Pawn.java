package org.example.chess.pieces;

import java.util.List;
import org.example.chess.board.Position;
import org.example.chess.pieces.enums.Direction;
import org.example.utils.MathUtils;

public class Pawn extends Piece {

    private static final double DIAGONAL_DISTANCE = 1.5;
    private boolean isFirstMove = true;

    public Pawn(Color color) {
        super(color, Type.PAWN);
    }

    @Override
    protected double getMaxDistance() {
        return isFirstMove ? 2 : DIAGONAL_DISTANCE;
    }

    @Override
    protected List<Direction> getDirections() {
        return this.getColor() == Color.BLACK ? Direction.blackPawnDirection() : Direction.whitePawnDirection();
    }

    public boolean isValidMove(Position from, Position to, List<Position> path, boolean isEnemyAtDestination) {
        if (isEnemyAtDestination) {
            return canCapture(from, to);
        }

        return canAdvance(from, to, path);
    }

    private boolean canAdvance(Position from, Position to, List<Position> path) {
        double distance = MathUtils.getDistance(from.getR(), from.getC(), to.getR(), to.getC());
        if (distance > getMaxDistance() || !path.isEmpty()) {
            return false;
        }

        for (Direction direction : getDirections()) {
            Position delta = to.delta(from);
            if (direction.isMovableDirection(delta) && !direction.isDiagonal()) {
                return true;
            }
        }
        return false;
    }

    private boolean canCapture(Position from, Position to) {
        double distance = MathUtils.getDistance(from.getR(), from.getC(), to.getR(), to.getC());
        if (MathUtils.greaterThan(distance, DIAGONAL_DISTANCE)) {
            return false;
        }

        for (Direction direction : getDirections()) {
            Position delta = to.delta(from);
            if (direction.isDiagonal() && direction.isMovableDirection(delta)) {
                return true;
            }
        }
        return false;
    }

    public void switchFlagToFalse() {
        this.isFirstMove = false;
    }
}
