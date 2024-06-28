package org.example.chess.board;

import static org.example.chess.board.Board.BOARD_SIZE;
import static org.example.utils.MathUtils.gcd;

import java.util.Objects;

public class Position {

    private final int r;
    private final int c;

    public Position(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public Position(String pos) {
        int r = BOARD_SIZE - (pos.charAt(1) - '1') - 1;
        int c = pos.charAt(0) - 'a';
        if (!isValidPosition(r, c)) {
            throw new IllegalArgumentException("올바른 위치를 입력해주세요");
        }
        this.r = r;
        this.c = c;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    private boolean isValidPosition(int r, int c) {
        return r >= 0 && r < BOARD_SIZE && c >= 0 && c < BOARD_SIZE;
    }

    public Position delta(Position other) {
        int deltaR = r - other.getR();
        int deltaC = c - other.getC();

        int gcd = gcd(Math.abs(deltaR), Math.abs(deltaC));

        if (deltaR == 0) {
            deltaC = deltaC > 0 ? 1 : -1;
        } else if (deltaC == 0) {
            deltaR = deltaR > 0 ? 1 : -1;
        } else {
            deltaR /= gcd;
            deltaC /= gcd;
        }
        return new Position(deltaR, deltaC);
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
        return r == position.r && c == position.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }
}
