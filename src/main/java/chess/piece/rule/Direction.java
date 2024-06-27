package chess.piece.rule;

import java.util.Arrays;
import java.util.List;

public enum Direction implements Moveable {
    NORTH(0, 1),
    NORTHEAST(1, 1),
    EAST(1, 0),
    SOUTHEAST(1, -1),
    SOUTH(0, -1),
    SOUTHWEST(-1, -1),
    WEST(-1, 0),
    NORTHWEST(-1, 1);

    private final int xDegree;
    private final int yDegree;

    Direction(int xDegree, int yDegree) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
    }

    @Override
    public int getXDegree() {
        return xDegree;
    }

    @Override
    public int getYDegree() {
        return yDegree;
    }

    public static List<Moveable> linearDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }

    public static List<Moveable> diagonalDirection() {
        return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }
}
