import chess.Board;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        while(true) {
            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNext()) {
                String input = scanner.nextLine();
                if (Objects.equals("start", input)) {
                    board.initialize();
                    System.out.println(board.print());
                }
                else if (Objects.equals("end", input)) {
                    return ;
                }
                else {
                    System.out.println("not in else if = " + input);
                }
            }
        }
    }
}
