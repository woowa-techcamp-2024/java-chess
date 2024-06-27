package com.woowatechcamp.chess.pieces;

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

    NORTH_TWO(0, 2),
    SOUTH_TWO(0, -2),

    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1);

    private final int xDegree;
    private final int yDegree;

    private Direction(int xDegree, int yDegree) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
    }

    public int getXDegree() {
        return xDegree;
    }

    public int getYDegree() {
        return yDegree;
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
        return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
    }

    public static List<Direction> whitePawnDirection() {
        return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
    }

    public static List<Direction> blackPawnForwardDirection() {
        return Arrays.asList(NORTH, NORTH_TWO);
    }

    public static List<Direction> whitePawnForwardDirection() {
        return Arrays.asList(SOUTH, SOUTH_TWO);
    }

    public static List<Direction> blackPawnCaptureDirection() {
        return Arrays.asList(NORTHEAST, NORTHWEST);
    }

    public static List<Direction> whitePawnCaptureDirection() {
        return Arrays.asList(SOUTHEAST, SOUTHWEST);
    }

    public static Direction fromPositions(Position from, Position to) {
        int deltaX = to.getXPos() - from.getXPos();
        int deltaY = to.getYPos() - from.getYPos();

        return Arrays.stream(values())
                .filter(direction -> direction.getXDegree() == deltaX && direction.getYDegree() == deltaY)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No valid direction for the given positions"));
    }

    @Override
    public String toString() {
        return "Direction{" +
                "xDegree=" + xDegree +
                ", yDegree=" + yDegree +
                '}';
    }
}
