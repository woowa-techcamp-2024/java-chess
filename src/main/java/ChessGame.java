import chess.Game;
import chess.board.Board;

import chess.board.BoardMaker;
import java.io.IOException;
import java.util.Scanner;

public class ChessGame {
    public static void main(String[] args) {
        ChessView chessView = new ChessView();
        Scanner scanner = new Scanner(System.in);
        chessView.startMessage();

        Game game = new Game();
        while (true) {
            chessView.pressKeyMessage();
            Command command = Command.from(scanner.next());

            if (command == Command.START) {
                game.newGame();
            }
            if (command == Command.END) {
                break;
            }
            if (command == Command.MOVE) {
                String source = scanner.next();
                String target = scanner.next();

                game.move(source, target);
            }

            // Board 상황 출력
            chessView.print(game.print());
        }
    }
}
