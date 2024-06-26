package com.wootecam.chess.game;

import com.wootecam.chess.board.Board;
import com.wootecam.chess.board.BoardInitializer;
import com.wootecam.chess.board.Position;

public class ChessGame {
    private Board board;
    private final BoardInitializer boardInitializer;

    public ChessGame(BoardInitializer boardInitializer) {
        this.boardInitializer = boardInitializer;
    }

    public void start() {
        board = new Board();
        boardInitializer.initialize(board);

        printBoard();
    }

    public void move(String source, String target) {
        Position srcPos = new Position(source);
        Position trgPos = new Position(target);

        board.move(srcPos, trgPos);

        printBoard();
    }

    private void printBoard() {
        System.out.println(board.print());
    }

    public void end() {

    }
}

