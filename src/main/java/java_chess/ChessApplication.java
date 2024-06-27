package java_chess;

import java.util.Scanner;
import java_chess.application.ChessGame;
import java_chess.application.ChessView;
import java_chess.chess.Board;
import java_chess.chess.pieces.values.Location;

public class ChessApplication {


    public static void main(String[] args) {
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);
        ChessView chessView = new ChessView(board);
        try (var scanner = new Scanner(System.in)) {
            Command command;
            do {
                var fullCommand = scanner.nextLine();
                command = Command.of(fullCommand);
                switch (command) {
                    case START -> {
                        chessGame.startGame();
                        System.out.println(chessView.printBoard());
                    }
                    case MOVE -> {
                        var from = fullCommand.substring(5, 7);
                        var to = fullCommand.substring(8, 10);
                        chessGame.movePiece(Location.from(from), Location.from(to));
                        System.out.println(chessView.printBoard());
                    }
                    case END -> {
                    }
                    default -> System.out.println("Wrong Command input");
                }
            } while (!command.equals(Command.END));
        }
    }

    private enum Command {
        START("start"),
        END("end"),
        MOVE("move"),
        ERROR("error");

        private final String value;

        Command(String value) {
            this.value = value;
        }

        static Command of(String fullCommand) {
            for (Command command : values()) {
                if (fullCommand.startsWith(command.value)) {
                    return command;
                }
            }
            return ERROR;
        }
    }
}
