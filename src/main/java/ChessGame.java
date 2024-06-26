import chess.board.Board;

import java.io.IOException;
import java.util.Scanner;

public class ChessGame {
    public static void main(String[] args) throws IOException {
        ChessView chessView = new ChessView();
        Scanner scanner = new Scanner(System.in);
        chessView.startMessage();

        Board board = new Board();
        while (true) {
            chessView.pressKeyMessage();
            Command command = Command.from(scanner.next());

            if (command == Command.START) {
                board.initialize();
            }
            if (command == Command.END) {
                break;
            }
            if (command == Command.MOVE) {
                String source = scanner.next();
                String target = scanner.next();

                board.move(source, target);
            }

            // Board 상황 출력
            chessView.print(board.print());
        }
    }
}
