package com.woopaca.javachess;

import com.woopaca.javachess.chess.Board;
import com.woopaca.javachess.chess.ChessView;
import com.woopaca.javachess.chess.MoveCommand;
import com.woopaca.javachess.chess.PieceHandler;
import com.woopaca.javachess.chess.Position;
import com.woopaca.javachess.pieces.Color;
import com.woopaca.javachess.pieces.Piece;

import java.util.Scanner;

public class ChessGame {

    private static final String END_COMMAND = "end";
    private static final String START_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";

    private static final Scanner scanner = new Scanner(System.in);
    private static final Board board = new Board();
    private static final ChessView chessView = new ChessView(board);
    private static final PieceHandler pieceHandler = new PieceHandler(board);

    private static boolean isStarted = false;
    private static Color turn = Color.WHITE;

    public static void main(String[] args) {
        System.out.println("--- 체스 ---");
        System.out.print("게임 시작 - `start` | 게임 종료 - `end`: ");
        String input;

        do {
            input = scanner.nextLine();
            try {
                validateCommandPrefix(input);

                if (input.equalsIgnoreCase(START_COMMAND)) {
                    System.out.println("게임을 시작합니다.");
                    board.initialize();
                    System.out.println(chessView.showBoard());
                    isStarted = true;
                }

                if (input.startsWith(MOVE_COMMAND) && isStarted) {
                    MoveCommand moveCommand = new MoveCommand(input);
                    validateCommand(moveCommand);
                    pieceHandler.movePiece(moveCommand);
                    System.out.println(chessView.showBoard());
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!isEndGame(input));
        System.out.println("게임을 종료합니다.");
    }

    private static void validateCommandPrefix(String input) {
        if (input.startsWith(START_COMMAND) || input.startsWith(END_COMMAND) || input.startsWith(MOVE_COMMAND)) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 올바르지 않은 입력입니다.");
    }

    private static void validateCommand(MoveCommand moveCommand) {
        Position sourcePosition = moveCommand.getSourcePosition();
        Piece piece = board.findPiece(sourcePosition);
        if (piece.getColor() != turn) {
            throw new IllegalArgumentException(String.format("[ERROR] %s의 차례입니다.", turn));
        }
    }

    private static boolean isEndGame(String input) {
        return input.equalsIgnoreCase(END_COMMAND);
    }

}
