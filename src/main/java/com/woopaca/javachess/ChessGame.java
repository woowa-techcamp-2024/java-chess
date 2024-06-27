package com.woopaca.javachess;

import com.woopaca.javachess.chess.Board;
import com.woopaca.javachess.chess.ChessView;
import com.woopaca.javachess.chess.MoveCommand;

import java.util.Scanner;

public class ChessGame {

    private static final String END_COMMAND = "end";
    private static final String START_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";

    private static final Scanner scanner = new Scanner(System.in);
    private static final Board board = new Board();
    private static final ChessView chessView = new ChessView(board);

    private static boolean isStarted = false;

    public static void main(String[] args) {
        System.out.println("--- 체스 ---");
        System.out.print("게임 시작 - `start` | 게임 종료 - `end`: ");
        String input;

        do {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase(START_COMMAND)) {
                System.out.println("게임을 시작합니다.");
                board.initialize();
                System.out.println(chessView.showBoard());
                isStarted = true;
            }

            if (input.startsWith(MOVE_COMMAND) && isStarted) {
                MoveCommand moveCommand = new MoveCommand(input);
                board.move(moveCommand.getSourcePosition(), moveCommand.getTargetPosition());
                System.out.println(chessView.showBoard());
            }
        } while (!isEndGame(input));
        System.out.println("게임을 종료합니다.");
    }

    private static boolean isEndGame(String input) {
        return input.equalsIgnoreCase(END_COMMAND);
    }

}
