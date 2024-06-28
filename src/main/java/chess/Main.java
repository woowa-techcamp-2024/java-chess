package chess;

import chess.board.Board;
import chess.game.ChessGame;
import chess.game.IllegalMoveException;
import chess.view.ChessView;

import java.util.Scanner;

public class Main {

    public enum Command {
        START("start"),
        END("end"),
        MOVE("move");

        private final String command;

        Command(String command) {
            this.command = command;
        }

        public String getCommand() {
            return command;
        }
    }

    public static void main(String[] args) {
        ChessView chessView = new ChessView();
        ChessGame chessGame = new ChessGame(new Board());

        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("Input start or end!");

        while (true) {
            command = scanner.nextLine();
            if (Command.START.getCommand().equalsIgnoreCase(command)) {
                chessGame.getBoard().initialize();
            } else if (Command.END.getCommand().equalsIgnoreCase(command)) {
                System.out.println("Game end!");
                break;
            } else if(command.startsWith(Command.MOVE.getCommand())) {
                String[] commands = command.split(" ");
                if(commands.length != 3) {
                    System.out.println("move command requires 2 arguments");
                }
                try {
                    chessGame.move(commands[1], commands[2]);
                } catch (IllegalMoveException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Invalid input! input again!");
                continue;
            }
            System.out.println(chessView.showBoard(chessGame.getBoard()));
        }
    }

}
