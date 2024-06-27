package org.example.chess.pieces.global;

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

    private void validate(int row, int col) throws IllegalArgumentException {
        if (row < 0 || row >= 8 || col < 0 || col >= 8) {
            throw new IllegalArgumentException(String.format("%s %s 잘못된 위치 인자입니다.", row, col));
        }
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }
}
