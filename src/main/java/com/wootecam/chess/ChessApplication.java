package com.wootecam.chess;

import java.util.Scanner;

public class ChessApplication {


    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";

    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        Board board = new Board();

        while (isContinue(inputReader.nextLine())) {
            board.initialize();
            board.print();
        }
    }

    private static boolean isContinue(String input) {
        if (START_COMMAND.equals(input)) {
            return true;
        }
        if (END_COMMAND.equals(input)) {
            return false;
        }
        String message = String.format("잘못된 입력입니다. ('start', 'end'만 가능) input = %s", input);
        throw new IllegalArgumentException(message);
    }
}
