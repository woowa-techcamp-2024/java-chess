import chess.Board;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        boolean isStarted = false;
        while(true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNext()) {
                String input = scanner.nextLine();
                if (!isStarted && Objects.equals("start", input)) {
                    isStarted = true;
                    board.initialize();
                    System.out.println(board.showBoard());
                }
                else if (Objects.equals("end", input)) {
                    isStarted = false;
                    return ;
                }
                else if (isStarted && input.startsWith("move")) {
                    String[] cmd = input.split(" ");
                    board.move(cmd[1], cmd[2]);
                    System.out.println(board.showBoard());
                }
                else {
                    System.out.println("not in else if = " + input);
                }
            }
        }
    }
}
