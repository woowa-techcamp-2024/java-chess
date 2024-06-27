package chess;

import chess.config.AppConfig;
import chess.exception.InvalidMoveException;
import chess.view.ConsoleView;

public class Main {

    //TODO: 공격자 턴 관리
    public static void main(String[] args) {
        ChessGame chessGame = AppConfig.chessGame();

        if (!ConsoleView.init()) return;

        String chessBoard = chessGame.start();

        ConsoleView.printChessBoard(chessBoard);

        while (true) {
            String[] commands = ConsoleView.play();

            try {
                chessBoard = chessGame.play(commands[0], commands[1]);
            } catch (InvalidMoveException e) {
                ConsoleView.printErrorMessage(e.getMessage());
                continue;
            }

            ConsoleView.printChessBoard(chessBoard);

            if (chessGame.isCheckmate()) {
                break;
            }
        }
    }
}
