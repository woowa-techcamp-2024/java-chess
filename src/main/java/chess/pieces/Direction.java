package chess.pieces;

import chess.pieces.Position.Degree;
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
    WWS(-2, -1);

    private int xDegree;
    private int yDegree;

    Direction(int xDegree, int yDegree) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
    }

    public static List<Direction> rookDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }

    public static List<Direction> bishopDirection() {
        return Arrays.asList(NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
    }

    public static List<Direction> queenDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
    }

    public static List<Direction> knightDirection() {
        return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }

    public static List<Direction> kingDirection() {
        return Arrays.asList(NORTH, NORTHEAST, NORTHWEST, SOUTH, SOUTHEAST, SOUTHWEST, EAST, WEST);
    }

    public static List<Direction> whitePawnDirection() {
        return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
    }

    public static List<Direction> blackPawnDirection() {
        return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
    }

    public int getXDegree() {
        return xDegree;
    }

    public int getYDegree() {
        return yDegree;
    }

    private static Direction valueOf(int xDegree, int yDegree) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.xDegree == xDegree && direction.yDegree == yDegree)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static Direction valueOf(Degree degree) {
        return valueOf(degree.getXDegree(), degree.getYDegree());
    }

    public static Direction valueOfDiagonal(Degree degree) {
        if (!degree.isDiagonal()) {
            throw new IllegalArgumentException();
        }
        return valueOf(degree.getDegreeOne());
    }

    public static Direction valueOfLinear(Degree degree) {
        if (!degree.isLinear()) {
            throw new IllegalArgumentException();
        }
        return valueOf(degree.getDegreeOne());
    }

    public static Direction valueOfLEvery(Degree degree) {
        try {
            return valueOfLinear(degree);
        } catch (IllegalArgumentException e) {
            return valueOfDiagonal(degree);
        }
    }

}
