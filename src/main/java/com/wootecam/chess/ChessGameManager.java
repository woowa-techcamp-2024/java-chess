package com.wootecam.chess;

import com.wootecam.chess.board.BoardInitializer;
import com.wootecam.chess.board.ScoreCalculationRule;
import com.wootecam.chess.game.BoardState;
import com.wootecam.chess.game.ChessGame;
import com.wootecam.chess.game.ChessResult;
import com.wootecam.chess.io.Reader;
import com.wootecam.chess.io.Writer;
import com.wootecam.chess.move.PieceMovementManager;
import com.wootecam.chess.view.ChessView;

public class ChessGameManager {
    private static final String CMD_START = "start";
    private static final String CMD_END = "end";
    private static final String CMD_MOVE = "move";

    private final Reader reader = new Reader();
    private final Writer writer = new Writer();

    private final BoardInitializer boardInitializer = new BoardInitializer();
    private final ScoreCalculationRule scoreCalculationRule = new ScoreCalculationRule();
    private final PieceMovementManager pieceMovementManager = new PieceMovementManager();

    private final ChessView chessView = new ChessView(reader, writer);

    public static void main(String[] args) {
        ChessGameManager manager = new ChessGameManager();
        manager.run();
    }

    public void run() {
        try {
            playGame();
        } catch (Exception e) {
            chessView.printErrorMessage(e.getMessage());
        } finally {
            close();
        }
    }

    public void playGame() {
        ChessGame chessGame = null;
        boolean isFinished = false;

        while (!isFinished) {
            String cmd = chessView.readInput();

            switch (cmd) {
                case CMD_START:
                    chessGame = startGame();
                    break;
                case CMD_END:
                    endGame(chessGame);
                    isFinished = true;
                    break;
                default:
                    if (!cmd.startsWith(CMD_MOVE)) {
                        throw new IllegalArgumentException("Unknown command: " + cmd);
                    }
                    move(chessGame, cmd);
            }
        }
    }

    private ChessGame startGame() {
        chessView.printGreetMessage();

        ChessGame chessGame = new ChessGame(boardInitializer, scoreCalculationRule, pieceMovementManager);
        BoardState boardState = chessGame.start();

        chessView.printChessBoard(boardState);

        return chessGame;
    }

    private void move(ChessGame chessGame, String cmd) {
        validGameStarted(chessGame);

        String[] split = cmd.split(" ");
        String source = split[1];
        String target = split[2];

        BoardState boardState = chessGame.move(source, target);
        chessView.printChessBoard(boardState);
    }

    private void endGame(ChessGame chessGame) {
        validGameStarted(chessGame);

        ChessResult result = chessGame.end();
        chessView.printChessResult(result);
    }

    private void validGameStarted(ChessGame chessGame) {
        if (chessGame == null) {
            throw new IllegalStateException("You should start game first");
        }
    }

    private void close() {
        reader.close();
    }
}
