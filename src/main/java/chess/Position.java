package chess;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
