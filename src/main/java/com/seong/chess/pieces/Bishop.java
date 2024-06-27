package com.seong.chess.pieces;

import com.seong.chess.Position;

public class Bishop extends Piece {

    private static final char REPRESENTATION = 'b';
    private static final double DEFAULT_POINT = 3.0;

    private Bishop(Color color) {
        super(Type.BISHOP, color, REPRESENTATION, DEFAULT_POINT);
    }

    public static Bishop createBlack() {
        return new Bishop(Color.BLACK);
    }

    public static Bishop createWhite() {
        return new Bishop(Color.WHITE);
    }

    @Override
    public boolean isNotBlank() {
        return false;
    }

    @Override
    public Position nextPosition(String sourcePosition, Direction direction, int moveCount) {
        direction.checkBishop();
        Position position = Position.convert(sourcePosition);
        if (moveCount == 0) {
            return position;
        }
        Position nextPosition = new Position(position.col() + direction.col, position.row() + direction.row);
        return nextPosition(nextPosition.convert(), direction, moveCount - 1);
    }
}
