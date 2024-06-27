package java_chess;

import java.util.Scanner;
import java.util.regex.Pattern;
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
            Command command = Command.NONE;
            do {
                try {
                    var fullCommand = scanner.nextLine();
                    command = Command.of(fullCommand);
                    switch (command) {
                        case START -> {
                            chessGame.startGame();
                            chessView.printBoard();
                        }
                        case MOVE -> {
                            Command.verifyMoveCommand(fullCommand);
                            var from = fullCommand.substring(5, 7);
                            var to = fullCommand.substring(8, 10);
                            chessGame.movePiece(Location.from(from), Location.from(to));
                            chessView.printBoard();
                        }
                        default -> {
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while (!command.equals(Command.END));
        }
    }

    private enum Command {
        START("start"),
        END("end"),
        MOVE("move"),
        NONE("none");

        private static final Pattern MOVE_PATTERN = Pattern.compile("move [a-h][1-8] [a-h][1-8]");

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
            throw new IllegalArgumentException("Invalid command");
        }

        static void verifyMoveCommand(String fullCommand) {
            if (!MOVE_PATTERN.matcher(fullCommand).matches()) {
                throw new IllegalArgumentException("Invalid move command");
            }
        }
    }
}
