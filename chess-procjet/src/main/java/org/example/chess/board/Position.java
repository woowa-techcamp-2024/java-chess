package org.example.chess.board;

import static org.example.chess.board.Board.BOARD_SIZE;

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
        //TODO: 나이트나 폰 이외에는 여러칸이 이동 가능하므로 delta를 나눠서 방향을 나타내게 만들어야한다.
        return new Position(r - other.r, c - other.c);
    }
}
