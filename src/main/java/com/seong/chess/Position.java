package com.seong.chess;

public record Position(int col, int row) {

    public Position {
        validateRow(row);
        validateCol(col);
    }

    public static Position convert(String rawPosition) {
        int col = rawPosition.charAt(0) - 'a';
        int row = 8 - Character.getNumericValue(rawPosition.charAt(1));
        return new Position(col, row);
    }

    private void validateRow(int row) {
        if (row < 0 || row >= 8) {
            throw new IllegalArgumentException("체스 보드 행은 1이상, 8 이하입니다.");
        }
    }

    private void validateCol(int col) {
        if (col < 0 || col >= 8) {
            throw new IllegalArgumentException("체스 보드 열은 a이상, h 이하입니다.");
        }
    }

    public String convert() {
        char rawCol = (char) (col + 'a');
        char rawRow = (char) (7 - row + '1');
        return String.valueOf(rawCol) + rawRow;
    }
}
