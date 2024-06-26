package com.wootecam.chess.game;

import com.wootecam.chess.board.Board;
import com.wootecam.chess.board.BoardInitializer;
import com.wootecam.chess.board.Position;
import com.wootecam.chess.board.ScoreCalculationRule;

public class ChessGame {
    private final BoardInitializer boardInitializer;
    private final ScoreCalculationRule scoreCalculationRule;

    private Board board;

    public ChessGame(BoardInitializer boardInitializer, ScoreCalculationRule scoreCalculationRule) {
        this.boardInitializer = boardInitializer;
        this.scoreCalculationRule = scoreCalculationRule;
    }

    public void start() {
        board = new Board(scoreCalculationRule);
        boardInitializer.initialize(board);

        printBoard();
    }

    public void move(String source, String target) {
        Position srcPos = new Position(source);
        Position trgPos = new Position(target);
        board.move(srcPos, trgPos);

        printBoard();
    }

    public void end() {

    }

    private void printBoard() {
        System.out.println(board.print());
    }
}

