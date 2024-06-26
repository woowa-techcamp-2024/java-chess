package org.example.chess;

public class Position {
    private final char col;
    private final int row;

    public Position(String position) {
        // 길이가 2가 아니면 에러 터짐
        if (position.length() != 2) {
            throw new IllegalArgumentException("position length must be 2");
        }
        char col = position.charAt(0);
        int row = Character.getNumericValue(position.charAt(1));

        // 첫번째 글자가 a~h가 아니면 에러 터짐
        if(col < 'a' || col > 'h') {
            throw new IllegalArgumentException("first character must be a~h");
        }

        // 두번째 글자가 1~8이 아니면 에러 터짐
        if(row < 1 || row > 8) {
            throw new IllegalArgumentException("second character must be 1~8");
        }

        this.col = col;
        this.row = row;
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
}
