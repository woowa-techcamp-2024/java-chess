import application.ChessView;
import chess.Board;
import java.util.Scanner;

public class ChessApplication {

    public static void main(String[] args) {
        Board board = new Board();
        ChessView chessView = new ChessView(board);
        try (var scanner = new Scanner(System.in)) {
            String command;
            do {
                command = scanner.nextLine();
                switch (command) {
                    case "start" -> {
                        board.initialize();
                        System.out.println(chessView.printBoard());
                    }
                    case "end" -> {
                    }
                    default -> System.out.println("Wrong Command input");
                }
            } while (!command.equals("end"));
        }
    }
}
