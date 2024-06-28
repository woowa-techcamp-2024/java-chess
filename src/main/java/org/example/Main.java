package org.example;

import org.example.chess.Board;
import org.example.chess.Chess;
import org.example.chess.ChessGame;
import org.example.chess.ChessView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Enter input (type 'exit' to quit):");
        Chess chess = null;

        while (true) {
            input = scanner.nextLine(); // Read input from the user

            if (input.equals("start")) {
                System.out.println("initilizeing..");

                Board board = new Board();
                board.initialize();
                chess = new Chess(new ChessGame(board), new ChessView(board));
            } else if (input.equals("end")) {
                break;
            } else if (input.startsWith("move") && chess != null) {
                System.out.println("moving...");
                String[] splits = input.split(" ");
                String from = splits[1];
                String to = splits[2];

                try {
                    chess.move(from, to);
                    chess.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage() + " 이동에 실패하였습니다.");
                }
            } else {
                System.out.println("잘못된 명령입니다.");
            }
        }

        System.out.println("Program ended.");
        scanner.close(); // Close the scanner to release resources
    }
}