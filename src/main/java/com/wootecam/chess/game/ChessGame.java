package com.wootecam.chess.game;

import com.wootecam.chess.board.Board;
import com.wootecam.chess.board.BoardInitializer;

public class ChessGame {
    private Board board;
    private BoardInitializer boardInitializer;

    public ChessGame(BoardInitializer boardInitializer) {
        this.boardInitializer = boardInitializer;
    }

    public void start() {
        board = new Board();
        boardInitializer.initialize(board);

        System.out.println(board.print());
    }

    public void end() {

    }
}

