package com.wootecam.chess.game;

import com.wootecam.chess.board.Board;

public class ChessGame {
    private Board board;

    public void start() {
        board = new Board();
        board.initialize();

        System.out.println(board.print());
    }

    public void end() {

    }
}

