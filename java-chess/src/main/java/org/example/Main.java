package org.example;

import org.example.chess.Board;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Enter input (type 'exit' to quit):");

        while (true) {
            input = scanner.nextLine(); // Read input from the user

            if (input.equals("start")) {
                Board board = new Board();
                board.initialize();
            } else if (input.equals("end")) {
                break;
            }
        }

        System.out.println("Program ended.");
        scanner.close(); // Close the scanner to release resources
    }
}