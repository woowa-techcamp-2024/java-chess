package chess;

public class Position {
    private final int row;
    private final int column;

    public Position(final String position) {
        this.column = position.charAt(0) - 'a';
        this.row = position.charAt(1) - '1';
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
