package wootecamp.chess.pieces;

import wootecamp.chess.board.MoveVector;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    EEN(2, 1),
    EES(2, 1),
    WWN(-2, 1),
    WWS(-2, 1);


    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
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

    public static Optional<Direction> determineDirection(MoveVector moveVector) {
        if (moveVector.getDx() * moveVector.getDx() + moveVector.getDy() * moveVector.getDy() == 5) {
            return determineNightDirection(moveVector);
        }
        if (moveVector.getDx() == 0 || moveVector.getDy() == 0) {
            return determineLinearDirection(moveVector);
        }
        if (Math.abs(moveVector.calculateInclination()) == 1.0) {
            return determineDialognal(moveVector);
        }

        return Optional.empty();
    }

    private static Optional<Direction> determineNightDirection(MoveVector moveVector) {
        for (Direction direction : knightDirection()) {
            if (direction.x == moveVector.getDx() && direction.y == moveVector.getDy()) {
                return Optional.of(direction);
            }
        }
        return Optional.empty();
    }

    private static Optional<Direction> determineLinearDirection(MoveVector moveVector) {
        int dx = moveVector.getDx();
        int dy = moveVector.getDy();

        if (dy == 0) {
            dx /= Math.abs(dx);
        }
        if (dx == 0) {
            dy /= Math.abs(dy);
        }

        for (Direction direction : linearDirection()) {
            if (direction.x == dx && direction.y == dy) {
                return Optional.of(direction);
            }
        }

        return Optional.empty();
    }

    private static Optional<Direction> determineDialognal(MoveVector moveVector) {
        int dx = moveVector.getDx();
        int dy = moveVector.getDy();

        dx /= Math.abs(dx);
        dy /= Math.abs(dy);


        for (Direction direction : diagonalDirection()) {
            if (direction.x == dx && direction.y == dy) {
                return Optional.of(direction);
            }
        }

        return Optional.empty();
    }
}
