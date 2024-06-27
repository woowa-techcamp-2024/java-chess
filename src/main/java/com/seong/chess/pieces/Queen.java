package com.seong.chess.pieces;

import com.seong.chess.Position;

public class Queen extends Piece {

    private static final char REPRESENTATION = 'q';
    private static final double DEFAULT_POINT = 9.0;

    private Queen(Color color) {
        super(Type.QUEEN, color, REPRESENTATION, DEFAULT_POINT);
    }

    public static Queen createBlack() {
        return new Queen(Color.BLACK);
    }

    public static Queen createWhite() {
        return new Queen(Color.WHITE);
    }

    @Override
    public boolean isNotBlank() {
        return false;
    }

    @Override
    public Position nextPosition(String sourcePosition, Direction direction, int moveCount) {
        Position position = Position.convert(sourcePosition);
        if (moveCount == 0) {
            return position;
        }
        Position nextPosition = new Position(position.col() + direction.col, position.row() + direction.row);
        return nextPosition(nextPosition.convert(), direction, moveCount - 1);
    }
}
