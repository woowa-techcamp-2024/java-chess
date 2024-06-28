package org.example;

import java.util.Scanner;
import org.example.chess.board.Board;
import org.example.chess.board.BoardInitializeManager;
import org.example.chess.board.BoardMoveManager;
import org.example.chess.board.BoardScoreManager;
import org.example.chess.board.BoardView;

public class Game {

    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";

    private static BoardMoveManager boardMoveManager;
    private static BoardInitializeManager boardInitializeManager;
    private static BoardScoreManager boardScoreManager;
    private static BoardView boardView;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        boolean onPlaying = true;

        while (onPlaying) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("명령어를 입력해 주세요");
            System.out.println("게임 시작 : start | 게임 종료 : end");
            String command = scanner.nextLine().toLowerCase();

            switch (command) {
                case START -> startGame();
                case END -> onPlaying = false;
                default -> System.out.println("올바른 명령어를 입력해주세요.");
            }
        }
    }

    private static void startGame() {
        Board board = new Board();
        init(board);
        initializeBoard(board);

        boardView.print();

        while (true) {
            System.out.println("명렁어를 입력해 주세요 : ex) move a2 a4");
            String moveCommand = scanner.nextLine().toLowerCase();

            if (moveCommand.startsWith(MOVE)) {
                handleMoveCommand(moveCommand);
                continue;
            }

            if (moveCommand.equals(END)) {
                System.out.println("게임을 종료합니다.");
                break;
            }

            System.out.println("올바른 명령어를 입력해주세요");
        }
    }

    private static void handleMoveCommand(String moveCommand) {
        try {
            String[] parts = moveCommand.split(" ");
            if (parts.length != 3) {
                throw new IllegalArgumentException("올바른 명렁어 형식이 아닙니다. 사용법 : move <source> <destination>");
            }

            String source = parts[1];
            String destination = parts[2];
            boardMoveManager.move(source, destination);
            boardView.print();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            boardView.print();
        }
    }

    private static void init(Board board) {
        boardInitializeManager = new BoardInitializeManager(board);
        boardMoveManager = new BoardMoveManager(board);
        boardScoreManager = new BoardScoreManager(board);
        boardView = new BoardView(board);
    }

    private static void initializeBoard(Board board) {
        boardInitializeManager.initialize();
    }
}