package woowa.camp.chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import woowa.camp.pieces.Color;
import woowa.camp.pieces.Pawn;

public class Board {

    public static final int MAX_PAWN = 8;
    public static final int MAX_ROW = 8;
    public static final int MAX_COL = 8;

    private final List<List<Pawn>> board = new ArrayList<>(new ArrayList<>());

    private final List<Pawn> pawns = new ArrayList<>();

    public void add(final Pawn pawn) {
        pawns.add(pawn);
    }

    public int size() {
        return pawns.size();
    }

    public Pawn findPawn(final int pawnIndex) {
        validateFindPawn(pawnIndex);
        return pawns.get(pawnIndex);
    }

    private void validateFindPawn(final int pawnIndex) {
        final int pawnSize = size();
        if (isOutOfRange(pawnSize, pawnIndex)) {
            throw new IllegalArgumentException(String.format("범위를 벗어난 PawnIndex = %d, 현재 Pawn Size = %d",
                    pawnIndex, pawnSize));
        }
    }

    private boolean isOutOfRange(int size, int index) {
        return index < 0 || index >= size;
    }

    public void initialize() {
        initBoard();
        initPawns(1, Color.PAWN_BLACK);
        initPawns(6, Color.PAWN_WHITE);
    }

    private void initBoard() {
        for (int row = 0; row < MAX_ROW; row++) {
            List<Pawn> rows = new ArrayList<>(Collections.nCopies(8, null));
            board.add(rows);
        }
    }

    private void initPawns(final int initRow, final Color color) {
        IntStream.range(0, MAX_COL).forEach(col -> {
            final Pawn pawn = new Pawn(color);
            addPawn(initRow, col, pawn);
        });
    }

    private void addPawn(final int row, final int col, final Pawn pawn) {
        board.get(row).set(col, pawn);
        pawns.add(pawn);
    }

    public String getWhitePawnsResult() {
        final StringBuilder sb = new StringBuilder();

        pawns.stream()
                .filter(pawn -> pawn.getColor().equals(Color.PAWN_WHITE.getName()))
                .forEach(filteredPawn -> sb.append(filteredPawn.getRepresentation()));

        return sb.toString();
    }

    public String getBlackPawnsResult() {
        final StringBuilder sb = new StringBuilder();

        pawns.stream()
                .filter(pawn -> pawn.getColor().equals(Color.PAWN_BLACK.getName()))
                .forEach(filteredPawn -> sb.append(filteredPawn.getRepresentation()));

        return sb.toString();
    }

}
