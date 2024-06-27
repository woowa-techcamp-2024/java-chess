package org.example.chess;

import java.util.Objects;

public class Position {

    private final char col;
    private final int row;

    public Position(String position) {
        // 길이가 2가 아니면 에러 터짐
        if (position.length() != 2) {
            throw new IllegalArgumentException("position length must be 2");
        }
        char col = position.charAt(0);
        int row = position.charAt(1);

        // 첫번째 글자가 a~h가 아니면 에러 터짐
        if (col < 'a' || col > 'h') {
            throw new IllegalArgumentException("first character must be a~h");
        }

        // 두번째 글자가 1~8이 아니면 에러 터짐
        if (row < '1' || row > '8') {
            throw new IllegalArgumentException("second character must be 1~8");
        }

        this.col = col;
        this.row = Character.getNumericValue(row);
    }

    public char getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getColIdx() {
        return col - 'a';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position position)) {
            return false;
        }
        return col == position.col && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }

    public Position next(Direction direction) {
        int nextCol = col + direction.getXDegree();
        int nextRow = row + direction.getYDegree();

        if (nextCol < 'a' || nextCol > 'h' || nextRow < 1 || nextRow > 8) {
            return null;
        }

        return new Position(String.valueOf((char) nextCol) + nextRow);
    }
}
