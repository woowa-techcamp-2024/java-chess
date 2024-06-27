package com.wootecam.chess.game;

import com.wootecam.chess.ChessView;
import com.wootecam.chess.board.Board;
import com.wootecam.chess.board.BoardInitializer;
import com.wootecam.chess.board.Position;
import com.wootecam.chess.board.ScoreCalculationRule;
import com.wootecam.chess.move.PieceMovementManager;

public class ChessGame {
    private final BoardInitializer boardInitializer;
    private final ScoreCalculationRule scoreCalculationRule;
    private final PieceMovementManager pieceMovementManager;
    private final ChessView chessView;

    private Board board;

    public ChessGame(BoardInitializer boardInitializer,
                     ScoreCalculationRule scoreCalculationRule,
                     PieceMovementManager pieceMovementManager,
                     ChessView chessView) {
        this.boardInitializer = boardInitializer;
        this.scoreCalculationRule = scoreCalculationRule;
        this.pieceMovementManager = pieceMovementManager;
        this.chessView = chessView;
    }

    public void start() {
        board = new Board();
        boardInitializer.initialize(board);

        chessView.printChessBoard(board);
    }

    public void move(String source, String target) {
        Position srcPos = new Position(source);
        Position trgPos = new Position(target);
        pieceMovementManager.move(board, srcPos, trgPos);

        chessView.printChessBoard(board);
    }

    public void end() {

    }
}

