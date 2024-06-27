package chess;

import chess.view.*;
import chess.view.console.ConsoleGameView;
import chess.view.console.ConsoleInputView;
import chess.view.console.ConsoleOutputView;
import chess.view.swing.SwingGameViewAndManager;

public class Main {
    private final ChessGame chessGame;
    private final Board board;
    private final GameManager gameManager;
    public Main(){
        board = new Board(new PieceCreatorWithFactory());
        chessGame = new ChessGame(board);
        gameManager = swingGameManager();

        gameManager.runningGame();
    }

    private GameManager consoleGameManager(){
        GameInputView gameInputView = new ConsoleInputView();
        GameOutputView gameOutputView = new ConsoleOutputView();
        GameView gameView = new ConsoleGameView(gameInputView, gameOutputView);

        return new ConsoleGameManager(gameView,chessGame);
    }

    private GameManager swingGameManager(){
        return new SwingGameViewAndManager(chessGame);
    }

    public static void main(String[] args) {
        new Main();
    }
}
