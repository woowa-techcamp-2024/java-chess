package chess;

import java.util.Objects;

public class Position {
    private final int row;
    private final int column;


    public Position(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

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
    public String toString() {
        return Character.toString('a' + column) + Character.toString('1' + row);
    }

    public static Position calculateDistance(final Position source, final Position destination) {
        return new Position(destination.getRow() - source.getRow(), destination.getColumn() - source.getColumn());
    }

    public double calcDistance(final Position target) {
        return Math.sqrt(Math.pow(row - target.row, 2) + Math.pow(column - target.getColumn(), 2));
    }

    public Position clone() {
        return new Position(this.row, this.column);
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

    public Position add(Position dir) {
        return new Position(row + dir.getRow(), column + dir.getColumn());
    }
}
