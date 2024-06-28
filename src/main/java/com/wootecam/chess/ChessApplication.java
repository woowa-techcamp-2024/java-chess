package com.wootecam.chess;

import com.wootecam.chess.board.Board;
import com.wootecam.chess.board.Rank;
import com.wootecam.chess.pieces.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

public class ChessApplication {

    private static final String[] ORDER = {"WHITE", "BLACK"};
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";
    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        Board board = initialize();
        CoordinatesExtractor extractor = new CoordinatesExtractor();
        PieceMoveVerifier pieceMoveVerifier = new PieceMoveVerifier();
        Game game = new Game(board, extractor, pieceMoveVerifier);

        ChessView chessView = new ChessView();

        chessView.printStartMessage();
        while (isContinue(inputReader.nextLine())) {
            chessView.printBoard(board.getRanks());

            chessView.printCommandInput();
            String command = inputReader.nextLine();
            String currentOrder = ORDER[COUNTER.getAndIncrement() % ORDER.length];

            if (!command.startsWith(MOVE_COMMAND)) {
                throw new IllegalArgumentException("'move {좌표1} {좌표2}'와 같이 입력해야 합니다.");
            }
            moveCommand(command, game, currentOrder);

            chessView.printBoard(board.getRanks());
        }
    }

    private static Board initialize() {

        List<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.createBlackOtherPieces());
        ranks.add(Rank.createPawns(Color.BLACK));
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createPawns(Color.WHITE));
        ranks.add(Rank.createWhiteOtherPieces());

        return new Board(ranks);
    }

    private static boolean isContinue(String input) {
        if (START_COMMAND.equals(input)) {
            return true;
        }
        if (END_COMMAND.equals(input)) {
            return false;
        }
        String message = String.format("잘못된 입력입니다. ('%s', '%s'만 가능) input = %s", START_COMMAND, END_COMMAND, input);
        throw new IllegalArgumentException(message);
    }

    private static void moveCommand(final String command, final Game game, final String currentOrder) {
        StringTokenizer moveToken = new StringTokenizer(command.substring(MOVE_COMMAND.length()));
        String startCoordinates = moveToken.nextToken();
        String targetCoordinates = moveToken.nextToken();

        game.move(startCoordinates, targetCoordinates, Color.valueOf(currentOrder));
    }
}
