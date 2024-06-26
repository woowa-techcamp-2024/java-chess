package com.wootecam.chess;

import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.Rank;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChessApplication {


    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";

    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        List<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.createBlackOtherPieces());
        ranks.add(Rank.createPawns(Color.BLACK));
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createPawns(Color.WHITE));
        ranks.add(Rank.createWhiteOtherPieces());
        CoordinatesExtractor extractor = new CoordinatesExtractor();

        Board board = new Board(ranks, extractor);

        while (isContinue(inputReader.nextLine())) {
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
        String message = String.format("잘못된 입력입니다. ('%s', '%s'만 가능) input = %s", START_COMMAND, END_COMMAND, input);
        throw new IllegalArgumentException(message);
    }
}
