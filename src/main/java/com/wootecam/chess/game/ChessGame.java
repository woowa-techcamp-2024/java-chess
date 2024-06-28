package com.wootecam.chess.game;

import static com.wootecam.chess.error.ErrorMessage.INVALID_TURN;

import com.wootecam.chess.board.Board;
import com.wootecam.chess.board.BoardInitializer;
import com.wootecam.chess.board.Position;
import com.wootecam.chess.board.ScoreCalculationRule;
import com.wootecam.chess.move.PieceMovementManager;
import com.wootecam.chess.pieces.property.Color;

public class ChessGame {

    private final BoardInitializer boardInitializer;
    private final ScoreCalculationRule scoreCalculationRule;
    private final PieceMovementManager pieceMovementManager;

    private Board board;
    private Color turn;

    public ChessGame(BoardInitializer boardInitializer,
                     ScoreCalculationRule scoreCalculationRule,
                     PieceMovementManager pieceMovementManager) {
        this.boardInitializer = boardInitializer;
        this.scoreCalculationRule = scoreCalculationRule;
        this.pieceMovementManager = pieceMovementManager;
        this.turn = Color.WHITE;
    }

    public BoardState start() {
        board = new Board();
        boardInitializer.initialize(board);

        return new BoardState(board.getState());
    }

    public BoardState move(String source, String target) {
        Position srcPos = new Position(source);
        Position trgPos = new Position(target);

        validTurn(srcPos);
        pieceMovementManager.move(board, srcPos, trgPos);

        turn = turn.toggle();

        return new BoardState(board.getState());
    }

    private void validTurn(Position position) {
        if (board.isEmpty(position) || !board.get(position).isColor(turn)) {
            throw new IllegalStateException(INVALID_TURN.value);
        }
    }

    public ChessResult end() {
        double whiteScore = board.calculateScore(scoreCalculationRule, Color.WHITE);
        double blackScore = board.calculateScore(scoreCalculationRule, Color.BLACK);
        Color winner = determineWinner(whiteScore, blackScore);

        return new ChessResult(whiteScore, blackScore, winner);
    }

    private Color determineWinner(double whiteScore, double blackScore) {
        if (whiteScore == blackScore) {
            return Color.NO_COLOR;
        }

        if (whiteScore > blackScore) {
            return Color.WHITE;
        }

        return Color.BLACK;
    }
}