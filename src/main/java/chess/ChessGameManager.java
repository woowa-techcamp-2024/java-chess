package chess;

import java.util.Scanner;

public class ChessGameManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("Input start or end!");
        while (true) {
            command = scanner.next();
            Board board = new Board();
            if (command.equalsIgnoreCase("start")) {
                board.initialize();
            } else if (command.equalsIgnoreCase("end")) {
                System.out.println("Game end!");
                break;
            } else {
                System.out.println("Invalid input! input again!");
                continue;
            }
            System.out.println(board.print());
        }
    }

}
