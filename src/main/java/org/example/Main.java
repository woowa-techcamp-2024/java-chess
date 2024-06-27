package org.example;

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
        boolean isGameEnded = false;

        while (!isGameEnded) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a command: ");
            String[] cmds = scanner.nextLine().split(" ");

            Command command = Command.fromString(cmds[0]);

            switch (command) {
                case START:
                    board = new Board();
                    chessGame = new ChessGame(board);
                    isGameStarted = true;
                    System.out.println("Game started.");
                    System.out.println(showBoard(chessGame.getBoard()));
                    break;
                case MOVE:
                    if (isGameStarted) {
                        if (cmds.length != 3) {
                            System.out.println("Invalid command");
                            continue;
                        }
                        chessGame.move(cmds[1], cmds[2]);

                        System.out.println(showBoard(chessGame.getBoard()));
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