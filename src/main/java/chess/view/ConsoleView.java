package chess.view;

import chess.piece.PieceColor;

import java.util.Scanner;

public class ConsoleView {

    private static final String START = "start";
    private static final String END = "end";
    private static final String START_MESSAGE = "게임을 시작하시고 싶다면 'start' 또는 하지 않으시려면 'end'를 입력해주세요.";
    private static final String INIT_FAIL_MESSAGE = "잘못 입력하셨습니다. 'start' 또는 'end'를 입력해주세요.";
    private static final String END_MESSAGE = "게임이 종료되었습니다.";
    private static final String MOVE_MESSAGE = "이동하고 싶은 말의 위치와 이동할 위치를 입력해주세요. ex) move a1 a2";
    private static final String MOVE = "move";
    private static final String MOVE_FAIL_MESSAGE = "잘못 입력하셨습니다. 다시 입력해주세요.";
    private static final String CHESS_BOARD_MESSAGE = "체스판의 상태입니다.";
    private static final String TURN_MESSAGE = " 차례입니다.";
    private static final Scanner scanner = new Scanner(System.in);

    private ConsoleView() {
    }

    public static boolean init() {
        System.out.println(START_MESSAGE);

        while (true) {
            String command = scanner.nextLine().trim();
            if (command.equals(START)) {
                return true;
            } else if (command.equals(END)) {
                System.out.println(END_MESSAGE);
                return false;
            }

            System.out.println(INIT_FAIL_MESSAGE);
        }
    }

    public static String[] play(PieceColor turn) {
        System.out.println(MOVE_MESSAGE);
        System.out.println(turn + TURN_MESSAGE);

        return splitCommand();
    }

    public static void printChessBoard(final String chessBoard) {
        System.out.println(CHESS_BOARD_MESSAGE);
        System.out.println(chessBoard);
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    private static String[] splitCommand() {
        while (true) {
            String command = scanner.nextLine().trim();

            if (!command.startsWith(MOVE)) {
                System.out.println(MOVE_FAIL_MESSAGE);
                continue;
            }
            command = command.replaceFirst(MOVE, "").trim();

            String[] commands = command.split(ChessView.SPACE);

            if (commands.length != 2) {
                System.out.println(MOVE_FAIL_MESSAGE);
                continue;
            }

            return commands;
        }
    }

}
