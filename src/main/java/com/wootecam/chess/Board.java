package com.wootecam.chess;

import static com.wootecam.chess.utils.StringUtils.appendNewLine;

import com.wootecam.chess.pieces.Pawn;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Board {

    private static final String EMPTY_PIECES_RESULTS = "........";
    private static final int PAWN_COUNT = 8;

    private final List<Pawn> blackPawns = new ArrayList<>();
    private final List<Pawn> whitePawns = new ArrayList<>();

    public Board() {
    }

    public void initialize() {
        blackPawns.addAll(createPawns(Pawn.COLOR_BLACK, Pawn.BLACK_REPRESENTATION));
        whitePawns.addAll(createPawns(Pawn.COLOR_WHITE, Pawn.WHITE_REPRESENTATION));
    }

    private List<Pawn> createPawns(String color, String representation) {
        return IntStream.range(0, PAWN_COUNT)
                .mapToObj(i -> new Pawn(color, representation))
                .toList();
    }

    public void add(final Pawn pawn) {
        whitePawns.add(pawn);
    }

    public Pawn findPawn(final int pawnIndex) {
        if (pawnIndex < 0 || whitePawns.size() <= pawnIndex) {
            String message = String.format("폰 인덱스는 0미만이거나 폰의 개수보다 크거나 같을 수 없습니다. size = %d", whitePawns.size());
            throw new IllegalArgumentException(message);
        }

        return whitePawns.get(pawnIndex);
    }

    public void print() {
        StringBuilder boardResults = new StringBuilder();

        boardResults.append(appendNewLine(EMPTY_PIECES_RESULTS))
                .append(appendNewLine(getBlackPawnsResults()))
                .append(appendNewLine(EMPTY_PIECES_RESULTS))
                .append(appendNewLine(EMPTY_PIECES_RESULTS))
                .append(appendNewLine(EMPTY_PIECES_RESULTS))
                .append(appendNewLine(EMPTY_PIECES_RESULTS))
                .append(appendNewLine(getWhitePawnsResults()))
                .append(appendNewLine(EMPTY_PIECES_RESULTS));

        System.out.println(boardResults);
    }

    public int size() {
        return whitePawns.size();
    }

    public String getWhitePawnsResults() {
        StringBuilder results = createPawnsResults(whitePawns);

        return results.toString();
    }

    public String getBlackPawnsResults() {
        StringBuilder results = createPawnsResults(blackPawns);

        return results.toString();
    }

    private StringBuilder createPawnsResults(List<Pawn> pawns) {
        StringBuilder results = new StringBuilder();
        pawns.forEach(pawn -> results.append(pawn.getRepresentation()));

        return results;
    }
}
