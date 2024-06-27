package com.seong.chess.pieces;

public enum Direction {
    NORTH(-1, 0),
    SOUTH(1, 0),
    EAST(0, 1),
    WEST(0, -1),
    NORTHEAST(-1, 1),
    SOUTHEAST(1, 1),
    SOUTHWEST(1, -1),
    NORTHWEST(-1, -1);

    final int row;
    final int col;

    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void checkBishop() {
        if (this == NORTH || this == SOUTH || this == EAST || this == WEST) {
            throw new IllegalArgumentException("비숍은 정방향으로 이동할 수 없습니다.");
        }
    }
}
