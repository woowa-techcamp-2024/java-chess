package com.wootecam.chess.pieces;

import java.util.List;

public enum Direction {
    NORTH(0, -1),
    NORTHEAST(1, -1),
    EAST(1, 0),
    SOUTHEAST(1, 1),
    SOUTH(0, 1),
    SOUTHWEST(-1, 1),
    WEST(-1, 0),
    NORTHWEST(-1, -1),

    NNE(1, -2),
    NNW(-1, -2),
    SSE(1, 2),
    SSW(-1, 2),
    EEN(2, -1),
    EES(2, 1),
    WWN(-2, -1),
    WWS(-2, 1);

    private final int column;
    private final int row;

    Direction(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public static Direction getPawnFirstMoveDirection(final Color color) {
        if (color == Color.BLACK) {
            return SOUTH;
        }
        return NORTH;
    }

    public static List<Direction> linearDirection() {
        return List.of(NORTH, EAST, SOUTH, WEST);
    }

    public static List<Direction> diagonalDirection() {
        return List.of(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> everyDirection() {
        return List.of(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> knightDirection() {
        return List.of(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }

    public static List<Direction> whitePawnDirection() {
        return List.of(NORTH, NORTHEAST, NORTHWEST);
    }

    public static List<Direction> blackPawnDirection() {
        return List.of(SOUTH, SOUTHEAST, SOUTHWEST);
    }

    public boolean isPawnAttackDirection() {
        return this == NORTHEAST || this == NORTHWEST || this == SOUTHEAST || this == SOUTHWEST;
    }

    public boolean isPawnMoveDirection() {
        return this == NORTH || this == SOUTH;
    }
}
