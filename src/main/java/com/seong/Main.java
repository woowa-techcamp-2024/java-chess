package com.seong;

import com.seong.chess.Board;
import com.seong.chess.ChessGame;
import com.seong.chess.Turn;
import java.util.Scanner;

public class Main {

    public static final String START = "start";
    public static final String END = "end";
    public static final String MOVE = "move";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);
        Turn turn = Turn.start();
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals(START)) {
                chessGame.initialize();
            } else if (command.equals(END)) {
                break;
            } else if (command.startsWith(MOVE)) {
                String[] commands = command.split(" ");
                String sourcePosition = commands[1];
                String targetPosition = commands[2];
                try {
                    chessGame.checkRightPlayerTurn(turn, sourcePosition);
                    chessGame.move(sourcePosition, targetPosition);
                    turn.change();
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("유효하지 않은 명령입니다.");
            }
            System.out.println(board.showBoard());
        }
    }
}