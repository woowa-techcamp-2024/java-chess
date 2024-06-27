package org.example;

import static java.lang.System.exit;
import static org.example.utils.BoardPrinter.showBoard;

import java.util.Scanner;
import org.example.chess.Board;
import org.example.chess.ChessGame;
import org.example.chess.Position;
import org.example.utils.Command;

public class Main {

    public static void main(String[] args) {
        Board board = null;
        ChessGame chessGame = null;
        boolean isGameStarted = false;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a command: ");
            String[] cmds = scanner.nextLine().split(" ");

            Command command = Command.fromString(cmds[0]);

            switch (command) {
                case START:
                    board = new Board();
                    chessGame = new ChessGame(board);
                    chessGame.initialize();
                    isGameStarted = true;
                    System.out.println("Game started.");
                    break;
                case MOVE:
                    if (isGameStarted) {
                        if (cmds.length != 3) {
                            System.out.println("Invalid command");
                            continue;
                        }

                        try {
                            chessGame.move(cmds[1], cmds[2]);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                    } else {
                        System.out.println("Game has not started yet.");
                    }
                    break;
                case END:
                    exit(0);
                    break;
                case INVALID:
                default:
                    System.out.println("Invalid command");
                    break;
            }

            System.out.println(showBoard(chessGame.getBoard()));
        }
    }
}