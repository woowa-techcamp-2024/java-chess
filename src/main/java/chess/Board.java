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

    private static final String EMPTY_ROW = "........";


    public void initialize() {
        initializePawns(WHITE_PAWN_ROW, Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
        initializePawns(BLACK_PAWN_ROW, Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
    }

    public String print() {
        StringBuilder sb = new StringBuilder();

        for (List<Pawn> pawn : pawns) {
            if(pawn.isEmpty()) {
                sb.append(EMPTY_ROW).append("\n");
                continue;
            }
            pawn.stream()
                    .map(Pawn::getRepresentation)
                    .forEach(sb::append);
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getWhitePawnsResult() {
        return getRowPawnsResult(WHITE_PAWN_ROW);
    }

    public String getBlackPawnsResult() {
        return getRowPawnsResult(BLACK_PAWN_ROW);
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

    private String getRowPawnsResult(int row) {
        validateIndex(row);
        return pawns.get(row).stream()
                .map(Pawn::getRepresentation)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private void initializePawns(int row, String color, char representation) {
        ArrayList<Pawn> initializedPawns = IntStream.range(0, BOARD_WIDTH)
                .mapToObj(i -> new Pawn(color, representation))
                .collect(Collectors.toCollection(ArrayList::new));
        this.pawns.get(row).addAll(initializedPawns);
    }

    private void validateIndex(int index) {
        if (index < 0 || index / BOARD_WIDTH >= BOARD_HEIGHT) {
            throw new IllegalArgumentException("Index out of range");
        }
    }
}
