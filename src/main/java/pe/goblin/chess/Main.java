package pe.goblin.chess;

import pe.goblin.chess.board.Board;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Goblin Chess");
        System.out.println("press start or end");
        String input;
        while (!"end".equalsIgnoreCase(input = scanner.nextLine())) {
            if ("start".equalsIgnoreCase(input)) {
                Board board = new Board();
                board.initialize();
                System.out.println(board.showBoard());
            } else {
                System.out.println("Invalid input");
            }
        }
    }
}