package chess;

import chess.config.AppConfig;
import chess.piece.PieceColor;
import chess.view.ConsoleView;

public class Main {

    public static void main(String[] args) {
        ChessGame chessGame = AppConfig.chessGame();

        if (!ConsoleView.init()) return;

        String chessBoard = chessGame.start();

        ConsoleView.printChessBoard(chessBoard);

        PieceColor turn = PieceColor.WHITE;

        while (true) {
            String[] commands = ConsoleView.play(turn);

            try {
                chessBoard = chessGame.play(commands[0], commands[1], turn);

                if (chessGame.isPromotion(commands[1])) {
                    String promotionPiece = ConsoleView.promotion();
                    chessGame.promotion(commands[1], promotionPiece);
                }
            } catch (RuntimeException e) {
                ConsoleView.printErrorMessage(e.getMessage());
                continue;
            }

            ConsoleView.printChessBoard(chessBoard);

            if (chessGame.isCheckmate(turn)) {
                break;
            }

            turn = turn.flip();
        }

        ConsoleView.checkmate(turn);
    }

}
