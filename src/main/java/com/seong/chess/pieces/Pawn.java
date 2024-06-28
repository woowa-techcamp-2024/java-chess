package com.seong.chess.pieces;

import com.seong.chess.Position;
import java.util.List;

public class Pawn extends Piece {

    private static final char REPRESENTATION = 'p';
    public static final double DEFAULT_POINT = 1.0;

    private Pawn(Color color) {
        super(color, REPRESENTATION, DEFAULT_POINT);
    }

    public static Pawn createWhite() {
        return new Pawn(Color.WHITE);
    }

    public static Pawn createBlack() {
        return new Pawn(Color.BLACK);
    }

    @Override
    public boolean isNotBlank() {
        return true;
    }

    @Override
    public boolean isPiecesDirection(Direction direction) {
        if (color == Color.WHITE && direction == Direction.NORTH) {
            return true;
        }
        if (color == Color.BLACK && direction == Direction.SOUTH) {
            return true;
        }
        return false;
    }

    @Override
    protected void findNextPositions(Position prevPosition, Direction direction, List<Position> positions) {
        if (Position.canNotMove(prevPosition.col() + direction.col, prevPosition.row() + direction.row)) {
            return;
        }
        Position nextPosition = new Position(prevPosition.col() + direction.col, prevPosition.row() + direction.row);
        positions.add(nextPosition);
        if (prevPosition.isPawnRow()) {
            findNextPositions(nextPosition, direction, positions);
        }
    }
}
