package woowa.camp.chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import woowa.camp.pieces.Color;
import woowa.camp.pieces.Piece;

public class Board {

    public static final int MAX_PAWN = 8;
    public static final int MAX_ROW = 8;
    public static final int MAX_COL = 8;

    private final List<List<Piece>> board = new ArrayList<>(new ArrayList<>());

    private final List<Piece> pieces = new ArrayList<>();

    public void add(final Piece piece) {
        pieces.add(piece);
    }

    public int size() {
        return pieces.size();
    }

    public Piece findPawn(final int pawnIndex) {
        validateFindPawn(pawnIndex);
        return pieces.get(pawnIndex);
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
            List<Piece> rows = new ArrayList<>(Collections.nCopies(8, null));
            board.add(rows);
        }
    }

    private void initPawns(final int initRow, final Color color) {
        IntStream.range(0, MAX_COL).forEach(col -> {
            final Piece piece = new Piece(color);
            addPawn(initRow, col, piece);
        });
    }

    private void addPawn(final int row, final int col, final Piece piece) {
        board.get(row).set(col, piece);
        pieces.add(piece);
    }

    public String getPawnsResult(final Color color) {
        final StringBuilder sb = new StringBuilder();
        appendPawnsResultFilteredByColor(color, sb);
        return sb.toString();
    }

    private void appendPawnsResultFilteredByColor(final Color color, final StringBuilder sb) {
        pieces.stream()
                .filter(isSameColor(color))
                .forEach(filteredPiece -> sb.append(filteredPiece.getRepresentation()));
    }

    private Predicate<Piece> isSameColor(final Color color) {
        return piece -> piece.getColor().equals(color.getName());
    }

    public String print() {
        final StringBuilder sb = new StringBuilder();
        for (int row = 0; row < MAX_ROW; row++) {
            appendRowRepresentation(row, sb);
            sb.append("\n");
        }
        return sb.toString();
    }

    private void appendRowRepresentation(int row, StringBuilder sb) {
        for (int col = 0; col < MAX_COL; col++) {
            getPawnByPosition(row, col)
                    .map(Piece::getRepresentation)
                    .ifPresentOrElse(sb::append, () -> sb.append("."));
        }
    }

    private Optional<Piece> getPawnByPosition(final int row, final int col) {
        return Optional.ofNullable(board.get(row).get(col));
    }

    public int getBoardRowSize() {
        return board.get(0).size();
    }

    public int getBoardColSize() {
        return board.size();
    }
}
