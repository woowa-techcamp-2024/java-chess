package com.woowatechcamp.chess;

import com.woowatechcamp.chess.pieces.Position;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        GameCommand gameCommand = GameCommand.fromString(scanner.nextLine());
        while (gameCommand == GameCommand.START) {
            playOneGame();
            gameCommand = GameCommand.fromString(scanner.nextLine());
        }
    }

    private static void playOneGame() {
        GameState gameState = GameState.PLAY;
        ChessGame chessGame = new ChessGame();
        while (gameState == GameState.PLAY) {
            try {
                String command = scanner.nextLine();
                GameCommand gameCommand = GameCommand.fromString(command);
                if (gameCommand == GameCommand.MOVE) {
                    String[] parts = command.split(" ");
                    if (parts.length != 3) {
                        System.out.println("Invalid move command. Please use format: move [source] [target]");
                        continue;
                    }
                    Position source = new Position(parts[1]);
                    Position target = new Position(parts[2]);
                    chessGame.move(source, target);
                }
                if (chessGame.isFinish()) {
                    gameState = GameState.FINISH;
                    System.out.println("Game finished!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }
}
