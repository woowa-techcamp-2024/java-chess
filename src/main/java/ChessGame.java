import chess.Board;

import java.io.IOException;
import java.util.Scanner;

public class ChessGame {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("체스게임시작~");

        String input;
        Board board = new Board();
        while (true) {
            System.out.println("start/move/end를 입력하세요");
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
            System.out.println(board.print());
        }
    }
}
