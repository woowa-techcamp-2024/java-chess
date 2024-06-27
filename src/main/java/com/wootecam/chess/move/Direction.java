package com.wootecam.chess.move;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(0, 1),
    NORTHEAST(1, 1),
    EAST(1, 0),
    SOUTHEAST(1, -1),
    SOUTH(0, -1),
    SOUTHWEST(-1, -1),
    WEST(-1, 0),
    NORTHWEST(-1, 1),

    EE(2, 0),
    WW(-2, 0),

    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1);

    public final int xDegree;
    public final int yDegree;

    Direction(int xDegree, int yDegree) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
    }

    public static List<Direction> whitePawnDirection() {
        return Arrays.asList(WEST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> linearDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }

    public static List<Direction> diagonalDirection() {
        return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> everyDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> knightDirection() {
        return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }

    public static List<Direction> blackPawnDirection() {
        return Arrays.asList(EAST, SOUTHEAST, NORTHEAST);
    }

    public boolean isDiagonal() {
        return this.equals(NORTHEAST) || this.equals(NORTHWEST) || this.equals(SOUTHEAST) || this.equals(SOUTHWEST);
    }

    public static List<Direction> kingDirections() {
        return everyDirection();
    }

    public static List<Direction> rookDirections() {
        return linearDirection();
    }

    public static List<Direction> bishopDirections() {
        return diagonalDirection();
    }

    public static List<Direction> queenDirections() {
        return everyDirection();
    }
}
