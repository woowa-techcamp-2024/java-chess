import chess.Board;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        try (var scanner = new Scanner(System.in)) {
            String command;
            do {
                command = scanner.nextLine();
                switch (command) {
                    case "start" -> {
                        board.initialize();
                        System.out.println(board.print());
                    }
                    case "end" -> {
                    }
                    default -> System.out.println("Wrong Command input");
                }
            } while (!command.equals("end"));
        }
    }
}
