package org.example.chess.pieces.global;

import java.util.Objects;

public class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        validate(row, col);
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public static Position of(int row, int col) {
        return new Position(row, col);
    }

    public static Position of(String position) {
        int col = position.charAt(0) - 'a';
        int row = 8 - Character.getNumericValue(position.charAt(1));
        return new Position(row, col);
    }

    public boolean movable(Direction dir) {
        try {
            validate(this.row + dir.getDr(), this.col + dir.getDc());
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public Position move(Direction dir) {
        return new Position(this.row + dir.getDr(), this.col + dir.getDc());
    }

    public void validate(int row, int col) throws IllegalArgumentException {
        if (isOutOfRange(row, col)) {
            throw new IllegalArgumentException(String.format("%s %s 잘못된 위치 인자입니다.", row, col));
        }
    }

    private static boolean isOutOfRange(int row, int col) {
        return row < 0 || row >= 8 || col < 0 || col >= 8;
    }

    public Position copy() {
        return new Position(this.row, this.col);
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
