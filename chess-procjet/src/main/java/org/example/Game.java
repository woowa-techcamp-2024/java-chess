package org.example;

import java.util.Scanner;
import org.example.chess.board.Board;
import org.example.chess.board.BoardInitializeManger;
import org.example.chess.board.BoardScoreManager;

public class Game {

    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";

    public static void main(String[] args) {
        boolean onPlaying = true;

        while (onPlaying) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("명령어를 입력해 주세요");
            System.out.println("게임 시작 : start | 게임 종료 : end");
            String command = scanner.nextLine().toLowerCase();

            if (command.equals(START)) {
                Board board = new Board(new BoardInitializeManger(), new BoardScoreManager());
                board.initialize();
                board.print();

                // move 명령을 받는다.
                String moveCommand = scanner.nextLine().toLowerCase();
                if (moveCommand.startsWith(MOVE)) {
                    String[] m = moveCommand.split(" ");
                    String source = m[1];
                    String destination = m[2];
                    board.move(source, destination);
                    board.print();
                }
                continue;
            }

            if (command.equals(END)) {
                onPlaying = false;
            }

            System.out.println("올바른 명령어를 입력해주세요");
        }
    }
}