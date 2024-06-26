package com.seong;

import com.seong.chess.Board;
import com.seong.chess.ChessGame;
import java.util.Scanner;

public class Main {

    public static final String START = "start";
    public static final String END = "end";
    public static final String MOVE = "move";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);
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
                chessGame.move(sourcePosition, targetPosition);
            }
            System.out.println(board.showBoard());
        }
    }
}