package chess;

import chess.config.AppConfig;
import chess.view.ConsoleView;

public class Main {

    public static void main(String[] args) {
        ChessGame chessGame = AppConfig.chessGame();

        if (!ConsoleView.init()) return;

        String chessBoard = chessGame.start();

        ConsoleView.printChessBoard(chessBoard);

        while (true) {
            String[] commands = ConsoleView.play();

            chessBoard = chessGame.play(commands[0], commands[1]);

            ConsoleView.printChessBoard(chessBoard);

            if (chessGame.isCheckmate()) {
                break;
            }
        }
    }
}
