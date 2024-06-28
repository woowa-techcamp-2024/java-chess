package woowa.camp;

import java.util.Scanner;
import woowa.camp.chess.Board;
import woowa.camp.chess.BoardGame;
import woowa.camp.chess.Command;

public class ChessGameStarter {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        gameStart(sc);
        sc.close();
    }

    private static void gameStart(final Scanner sc) {
        BoardGame boardGame = BoardGame.createWithInitialize(new Board());
        while (inputCommand(sc) == Command.START) {
            String print = boardGame.showBoard();
            System.out.println(print);

            move(sc, boardGame);
        }
    }

    private static void move(final Scanner sc, final BoardGame boardGame) {
        while (true) {
            String moveCommand = sc.nextLine();

            if (!moveCommand.startsWith("move")) {
                System.out.println("움직임 커맨드를 제대로 입력해주세요.");
                continue;
            }

            String[] s = moveCommand.split(" ");
            String sourcePosition = s[1];
            System.out.println("sourcePosition = " + sourcePosition);
            String destPosition = s[2];
            System.out.println("destPosition = " + destPosition);
//            board.move(sourcePosition, destPosition);
            System.out.println(boardGame.showBoard());
        }
    }

    private static Command inputCommand(final Scanner sc) {
        String startCommand = sc.next();
        return Command.fromName(startCommand);
    }
}
