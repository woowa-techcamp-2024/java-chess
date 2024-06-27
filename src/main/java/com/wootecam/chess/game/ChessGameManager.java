package com.wootecam.chess.game;

import com.wootecam.chess.board.BoardInitializer;
import com.wootecam.chess.board.ScoreCalculationRule;
import com.wootecam.chess.common.Reader;
import com.wootecam.chess.move.PieceMovementManager;
import com.wootecam.chess.view.ChessView;

public class ChessGameManager {
    private static final String CMD_START = "start";
    private static final String CMD_END = "end";
    private static final String CMD_MOVE = "move";

    private static final BoardInitializer boardInitializer = new BoardInitializer();
    private static final ScoreCalculationRule scoreCalculationRule = new ScoreCalculationRule();
    private static final PieceMovementManager pieceMovementManager = new PieceMovementManager();
    private static final ChessView chessView = new ChessView();

    private final Reader reader;

    public ChessGameManager(Reader reader) {
        this.reader = reader;
    }

    public static void main(String[] args) {
        Reader reader = new Reader();
        ChessGameManager manager = new ChessGameManager(reader);

        try {
            manager.playGame(reader);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            manager.close();
        }
    }

    public void playGame(Reader reader) {
        ChessGame chessGame = null;
        boolean isFinished = false;

        chessView.printGreetMessage();
        while (!isFinished) {
            System.out.print(">>> ");
            String cmd = reader.readLine();

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
        ChessGame chessGame = new ChessGame(boardInitializer, scoreCalculationRule, pieceMovementManager, chessView);
        chessGame.start();

        return chessGame;
    }

    private void move(ChessGame chessGame, String cmd) {
        validGameStarted(chessGame);

        String[] split = cmd.split(" ");
        String source = split[1];
        String target = split[2];

        chessGame.move(source, target);
    }

    private void endGame(ChessGame chessGame) {
        validGameStarted(chessGame);

        chessGame.end();
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
