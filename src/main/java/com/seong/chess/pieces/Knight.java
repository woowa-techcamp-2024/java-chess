package com.seong.chess.pieces;

import com.seong.chess.Position;
import java.util.List;

public class Knight extends Piece {

    private static final char REPRESENTATION = 'n';
    private static final double DEFAULT_POINT = 2.5;

    private Knight(Color color) {
        super(color, REPRESENTATION, DEFAULT_POINT);
    }

    public static Knight createWhite() {
        return new Knight(Color.WHITE);
    }

    public static Knight createBlack() {
        return new Knight(Color.BLACK);
    }

    @Override
    public boolean isNotBlank() {
        return true;
    }

    @Override
    protected boolean isPiecesDirection(Direction direction) {
        return !(direction.isRight() || direction.isDiagonal());
    }

    @Override
    protected void findNextPositions(Position prevPosition, Direction direction, List<Position> positions) {
        if (Position.canNotMove(prevPosition.col() + direction.col, prevPosition.row() + direction.row)) {
            return;
        }
        Position nextPosition = new Position(prevPosition.col() + direction.col, prevPosition.row() + direction.row);
        positions.add(nextPosition);
    }
}
