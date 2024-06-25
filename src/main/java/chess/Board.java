package chess;

import chess.pieces.Pawn;
import chess.pieces.Pawn.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Board {

    private static final int N = 8;

    private static final int BLACK_PAWN_START_INDEX = 8;

    private static final int BLACK_PAWN_END_INDEX = 15;

    private static final int WHITE_PAWN_START_INDEX = 48;

    private static final int WHITE_PAWN_END_INDEX = 55;

    private List<Pawn> board;


    public Board() {
        this.board = new ArrayList<>(Collections.nCopies(N * N, null));
    }

    public void add(Pawn pawn) {
        if (pawn == null) {
            throw new IllegalArgumentException("pawn must not be null");
        }
        board.add(pawn);
    }

    public Pawn findPawn(int index) {
        return board.get(index);
    }

    public int size() {
        return board.size();
    }

    public void initialize() {
        initializePawn(BLACK_PAWN_START_INDEX, BLACK_PAWN_END_INDEX, Color.BLACK);
        initializePawn(WHITE_PAWN_START_INDEX, WHITE_PAWN_END_INDEX, Color.WHITE);
    }

    public void initializePawn(int startIndexInclusive, int endIndexInclusive, Pawn.Color color) {
        IntStream.range(startIndexInclusive, endIndexInclusive + 1)
                .forEach(index -> board.add(index, new Pawn(color)));
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < N; row++) {
            sb.append(print(row * N, row * N + N - 1));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    private char getRepresentation(Pawn pawn) {
        return pawn != null ? pawn.getRepresentation() : '.';
    }

    public String print(int startIndexInclusive, int endIndexInclusive) {
        StringBuilder sb = new StringBuilder();
        for (int index = startIndexInclusive; index <= endIndexInclusive; index++) {
            sb.append(getRepresentation(board.get(index)));
        }
        return sb.toString();
    }

    public String getWhitePawnsResult() {
        return print(WHITE_PAWN_START_INDEX, WHITE_PAWN_END_INDEX);
    }

    public String getBlackPawnsResult() {
        return print(BLACK_PAWN_START_INDEX, BLACK_PAWN_END_INDEX);
    }
}
