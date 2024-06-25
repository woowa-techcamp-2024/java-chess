package org.example.chess.board;

import static org.example.chess.board.Board.BOARD_SIZE;

import java.security.PublicKey;

public class Position {

    private final int r;
    private final int c;

    public Position(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public Position(String pos) {
        this.r = BOARD_SIZE - (pos.charAt(1) - '1') - 1;
        this.c = pos.charAt(0) - 'a';
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }
}
