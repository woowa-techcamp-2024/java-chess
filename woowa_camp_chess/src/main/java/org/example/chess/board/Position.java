package org.example.chess.board;

import static org.example.chess.board.Board.*;

public class Position {
    private int row;
    private int column;

    private Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Position move(Direction direction) {
        return new Position(row + direction.getXDegree(), column + direction.getYDegree());
    }

    public static Position fromStr(String str) {
        int col = str.charAt(0) - 'a';
        int row = 7-(str.charAt(1) - '1');
        validPosition(col, row);
        return new Position(row, col);
    }

    public static boolean validPosition(int col, int row) {
        if(col < 0 || row < 0 || col >= SIZE || row >= SIZE) {
            return false;
        }
        return true;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
