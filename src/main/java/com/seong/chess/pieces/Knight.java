package com.seong.chess.pieces;

import com.seong.chess.Position;

public class Knight extends Piece {

    private static final char REPRESENTATION = 'n';
    private static final double DEFAULT_POINT = 2.5;

    private Knight(Color color) {
        super(Type.KNIGHT, color, REPRESENTATION, DEFAULT_POINT);
    }

    public static Knight createWhite() {
        return new Knight(Color.WHITE);
    }

    public static Knight createBlack() {
        return new Knight(Color.BLACK);
    }

    @Override
    public boolean isNotBlank() {
        return false;
    }

    @Override
    public Position nextPosition(String sourcePosition, Direction direction, int moveCount) {
        direction.checkKnight();
        Position position = Position.convert(sourcePosition);
        return new Position(position.col() + direction.col, position.row() + direction.row);
    }
}
