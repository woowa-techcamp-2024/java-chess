package org.example.chess;

import static java.lang.Integer.MAX_VALUE;

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

    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1),
    NOBODY(0, 0)
    ;

    private final int xDegree;
    private final int yDegree;

    Direction(int xDegree, int yDegree) {
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

    public static List<Direction> whitePawnDirection() {
        return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
    }

    public static List<Direction> blackPawnDirection() {
        return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
    }

    public static Direction determineDirection(Position start, Position end) {
        int deltaX = end.getCol() - start.getCol();
        int deltaY = end.getRow() - start.getRow();

        if (deltaX == 0 && deltaY == 0) {
            return Direction.NOBODY;
        }

        if(deltaX == 0) {
            return deltaY > 0 ? Direction.NORTH : Direction.SOUTH;
        }

        if(deltaY == 0) {
            return deltaX > 0 ? Direction.EAST : Direction.WEST;
        }

        int gcd = gcd(Math.abs(deltaX), Math.abs(deltaY));
        if (gcd != 0) {
            deltaX /= gcd;
            deltaY /= gcd;
        }



        for (Direction direction : Direction.values()) {
            if (direction.getXDegree() == deltaX && direction.getYDegree() == deltaY) {
                return direction;
            }
        }

        return Direction.NOBODY;
    }

    private static int gcd(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
