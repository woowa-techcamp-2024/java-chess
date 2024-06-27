package java_chess.chess.pieces.enums;

import java_chess.chess.pieces.values.Location;

public enum Direction {
    LEFT(0, -1),
    RIGHT(0, 1),
    UP(1, 0),
    DOWN(-1, 0),
    LEFT_UP(1, -1),
    LEFT_DOWN(-1, -1),
    RIGHT_UP(1, 1),
    RIGHT_DOWN(-1, 1),
    PAWN_BLACK_DOUBLE_DOWN(-2, 0),
    PAWN_WHITE_DOUBLE_UP(2, 0),
    KNIGHT_LEFT_UP(2, -1),
    KNIGHT_LEFT_DOWN(-2, -1),
    KNIGHT_DOWN_LEFT(-1, -2),
    KNIGHT_DOWN_RIGHT(-1, 2),
    KNIGHT_RIGHT_DOWN(1, 2),
    KNIGHT_RIGHT_UP(1, -2),
    KNIGHT_UP_RIGHT(2, 1),
    KNIGHT_UP_LEFT(-2, 1);

    private final int row;
    private final int col;

    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean canMove(Location current) {
        try {
            nextLocation(current);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public Location nextLocation(Location location) {
        return Location.of(location.getX() + row, (char) (location.getCurrentY() + col));
    }
}
