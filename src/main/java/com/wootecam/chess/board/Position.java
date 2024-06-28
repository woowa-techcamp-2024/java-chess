package com.wootecam.chess.board;

import static com.wootecam.chess.board.Board.MAX_ROW;
import static com.wootecam.chess.constraint.ChessConstraint.validIndex;
import static com.wootecam.chess.error.ErrorMessage.INVALID_POSITION;

import com.wootecam.chess.move.Direction;
import java.util.Objects;

public class Position {
    public final int x;
    public final int y;

    public Position(String position) {
        validPositionForm(position);

        this.x = MAX_ROW - (position.charAt(1) - '0');
        this.y = position.charAt(0) - 'a';
    }

    private Position(int x, int y) {
        validIndex(x, y);
        this.x = x;
        this.y = y;
    }

    private void validPositionForm(String position) {
        if (position.length() != 2) {
            throw new IllegalArgumentException(INVALID_POSITION.value);
        }

        char row = position.charAt(1);
        if (row < '1' || row > '8') {
            throw new IllegalArgumentException(INVALID_POSITION.value);
        }

        char col = position.charAt(0);
        if (col < 'a' || col > 'h') {
            throw new IllegalArgumentException(INVALID_POSITION.value);
        }
    }

    public Position moveBy(Direction direction) {
        return new Position(this.x + direction.xDegree, this.y + direction.yDegree);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
