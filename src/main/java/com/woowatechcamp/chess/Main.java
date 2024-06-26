package com.woowatechcamp.chess;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameState gameState = GameState.fromString(scanner.nextLine());
        while (gameState == GameState.START) {
            Board board = new Board();
            board.initialize();
            board.print();
            gameState = GameState.fromString(scanner.nextLine());
        }
    }
}
