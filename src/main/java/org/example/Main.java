package org.example;

import java.util.Scanner;
import org.example.chess.Board;
import org.example.chess.Position;
import org.example.utils.Command;

public class Main {

    public static void main(String[] args) {
        Board board = null;
        boolean isGameStarted = false;
        boolean isGameEnded = false;

        while (!isGameEnded) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a command: ");
            String[] cmds = scanner.nextLine().split(" ");

            Command command = Command.fromString(cmds[0]);

            switch (command) {
                case START:
                    board = new Board();
                    board.initialize();
                    isGameStarted = true;
                    System.out.println("Game started.");
                    System.out.println(board.showBoard());
                    break;
                case MOVE:
                    if (isGameStarted) {
                        if (cmds.length != 3) {
                            System.out.println("Invalid command");
                            continue;
                        }
                        board.move(new Position(cmds[1]), new Position(cmds[2]));

                        System.out.println(board.showBoard());
                    } else {
                        System.out.println("Game has not started yet.");
                    }
                    break;
                case END:
                    isGameEnded = true;
                    break;
                case INVALID:
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }
}