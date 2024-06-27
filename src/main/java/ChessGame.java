import chess.Game;

import chess.board.Position;
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
                Position source = Position.from(scanner.next());
                Position target = Position.from(scanner.next());

                game.move(source, target);
            }

            // Board 상황 출력
            chessView.showBoard(game.print());
        }
    }
}
