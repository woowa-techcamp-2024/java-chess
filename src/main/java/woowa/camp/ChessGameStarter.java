package woowa.camp;

import java.util.Scanner;
import woowa.camp.chess.Board;
import woowa.camp.chess.Command;

public class ChessGameStarter {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        gameStart(sc);
        sc.close();
    }

    private static void gameStart(final Scanner sc) {
        while (inputCommand(sc) == Command.START) {
            Board board = new Board();
            board.initialize();

            String print = board.print();
            System.out.println(print);
        }
    }

    private static Command inputCommand(final Scanner sc) {
        String startCommand = sc.next();
        return Command.fromName(startCommand);
    }
}
