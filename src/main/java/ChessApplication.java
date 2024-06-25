import static chess.CommandType.END;
import static chess.CommandType.START;

import chess.Board;
import java.util.Scanner;

public class ChessApplication {

    public static void main(String[] args) {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();

            if (START.getValue().equals(command)) {
                Board board = new Board();
                board.initialize();
                board.print();
            }
            if (END.getValue().equals(command)) {
                break;
            }
        }
    }
}
