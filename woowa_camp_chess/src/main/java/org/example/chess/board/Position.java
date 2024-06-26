package org.example.chess.board;

public class Position {
    private int row;
    private int column;

    private Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Position fromStr(String str) {
        int col = str.charAt(0) - 'a';
        int row = str.charAt(1) - '1';

        return new Position(row, col);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
