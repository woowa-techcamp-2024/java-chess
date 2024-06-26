package com.woowatechcamp.chess;

import com.woowatechcamp.chess.pieces.Position;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        GameCommand gameCommand = GameCommand.fromString(scanner.nextLine());
        while (gameCommand == GameCommand.START) {
            playOneGame();
            gameCommand = GameCommand.fromString(scanner.nextLine());
        }
    }

    private static void playOneGame() {
        GameState gameState = GameState.PLAY;
        Board board = new Board();
        board.initialize();
        while (gameState == GameState.PLAY) {
            board.print();
            String command = scanner.nextLine();
            GameCommand gameCommand = GameCommand.fromString(command);
            if (gameCommand == GameCommand.MOVE) {
                Position source = new Position(command.split(" ")[1]);
                Position target = new Position(command.split(" ")[2]);
                board.move(source, target);
            }
            if (isFinish()) {
                gameState = GameState.FINISH;
            }
        }
    }

    private static boolean isFinish() {
        return false;
    }
}
