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

    private final int fileDegree;
    private final int rankDegree;

    Direction(final int fileDegree, final int rankDegree) {
        this.fileDegree = fileDegree;
        this.rankDegree = rankDegree;
    }

    public static List<Moveable> linearDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }

    public static List<Moveable> diagonalDirection() {
        return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    @Override
    public int getRankDegree() {
        return rankDegree;
    }

    @Override
    public int getFileDegree() {
        return fileDegree;
    }
}
