package chess;

import chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private final List<List<Pawn>> pawns;

    private static final int BOARD_WIDTH = 8;
    private static final int BOARD_HEIGHT = 8;
    private static final int WHITE_PAWN_ROW = 1;
    private static final int BLACK_PAWN_ROW = 6;


    public void initialize() {
        initializeWhitePawns();
        initializeBlackPawns();
    }

    public String getWhitePawnsResult() {
        return pawns.get(WHITE_PAWN_ROW).stream()
                .map(Pawn::getRepresentation)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public String getBlackPawnsResult() {
        return pawns.get(BLACK_PAWN_ROW).stream()
                .map(Pawn::getRepresentation)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public int size() {
        return pawns.get(0).size();
    }

    public Pawn findPawn(int index) {
        return pawns.get(0).get(index);
    }

    public void add(Pawn pawn) {
        pawns.get(0).add(pawn);
    }
    public Board() {
        pawns = new ArrayList<>();
        IntStream.range(0, BOARD_HEIGHT)
                .forEach(i -> pawns.add(new ArrayList<>()));
    }

    private void initializeWhitePawns() {
        ArrayList<Pawn> whitePawns = IntStream.range(0, BOARD_WIDTH)
                .mapToObj(i -> new Pawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION))
                .collect(Collectors.toCollection(ArrayList::new));
        pawns.get(WHITE_PAWN_ROW).addAll(whitePawns);
    }

    private void initializeBlackPawns() {
        ArrayList<Pawn> blackPawns = IntStream.range(0, BOARD_WIDTH)
                .mapToObj(i -> new Pawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION))
                .collect(Collectors.toCollection(ArrayList::new));
        pawns.get(BLACK_PAWN_ROW).addAll(blackPawns);
    }
    private void validateIndex(int index) {
        if (index < 0 || index / BOARD_WIDTH >= BOARD_HEIGHT) {
            throw new IllegalArgumentException("Index out of range");
        }
    }
}
