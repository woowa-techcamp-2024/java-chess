package com.woopaca.javachess.chess;

import java.util.Scanner;

public class ChessGame {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Board board = new Board();

    public static void main(String[] args) {
        String input;
        do {
            System.out.print("Input: ");
            input = scanner.next();
            if (input.equals("end")) {
                continue;
            }

            board.initialize();
            System.out.println(board.print());
        } while (!input.equals("end"));
    }

}
