package com.seong.chess.pieces;

import com.seong.chess.Position;
import java.util.Arrays;

public enum Direction {
    NORTH(-1, 0),
    SOUTH(1, 0),
    EAST(0, 1),
    WEST(0, -1),
    NORTHEAST(-1, 1),
    SOUTHEAST(1, 1),
    SOUTHWEST(1, -1),
    NORTHWEST(-1, -1),

    NNE(-2, 1),
    EEN(-1, 2),
    EES(1, 2),
    SSE(2, 1),
    SSW(2, -1),
    WWS(1, -2),
    WWN(-1, -2),
    NNW(-2, -1);

    final int row;
    final int col;

    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isRight() {
        return this == NORTH || this == SOUTH || this == EAST || this == WEST;
    }

    public boolean isDiagonal() {
        return this == NORTHEAST || this == SOUTHEAST || this == NORTHWEST || this == SOUTHWEST;
    }

    public static boolean isNorthDiagonal(Position source, Position target) {
        return Arrays.stream(values())
                .filter(Direction::isNorthDiagonal)
                .anyMatch(direction -> {
                    Position diagonalPosition = new Position(
                            source.col() + direction.col, source.row() + direction.row);
                    return diagonalPosition.equals(target);
                });
    }

    private boolean isNorthDiagonal() {
        return this == NORTHEAST || this == NORTHWEST;
    }

    public static boolean isSouthDiagonal(Position source, Position target) {
        return Arrays.stream(values())
                .filter(Direction::isSouthDiagonal)
                .anyMatch(direction -> {
                    Position diagonalPosition = new Position(
                            source.col() + direction.col, source.row() + direction.row);
                    return diagonalPosition.equals(target);
                });
    }

    private boolean isSouthDiagonal() {
        return this == SOUTHEAST || this == SOUTHWEST;
    }
}
