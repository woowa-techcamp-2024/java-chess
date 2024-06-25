package chess;

import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static chess.utils.StringUtils.appendNewLine;

public class Board {

    private final List<List<Piece>> pawns;

    private static final int BOARD_WIDTH = 8;
    private static final int BOARD_HEIGHT = 8;
    private static final int WHITE_PAWN_ROW = 1;
    private static final int BLACK_PAWN_ROW = 6;

    private static final String EMPTY_ROW = "........";


    public void initialize() {
        initializeWhitePawns();
        initializeBlackPawns();
    }

    public String print() {
        StringBuilder sb = new StringBuilder();

        for (List<Piece> piece : pawns) {
            if(piece.isEmpty()) {
                sb.append(EMPTY_ROW);
                appendNewLine(sb);
                continue;
            }
            piece.stream()
                    .map(Piece::getRepresentation)
                    .forEach(sb::append);
            appendNewLine(sb);
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

    public Piece findPawn(int index) {
        return pawns.get(0).get(index);
    }

    public void add(Piece piece) {
        pawns.get(0).add(piece);
    }
    public Board() {
        pawns = new ArrayList<>();
        IntStream.range(0, BOARD_HEIGHT)
                .forEach(i -> pawns.add(new ArrayList<>()));
    }

    private String getRowPawnsResult(int row) {
        validateIndex(row);
        return pawns.get(row).stream()
                .map(Piece::getRepresentation)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private void initializeWhitePawns() {
        ArrayList<Piece> initializedPieces = IntStream.range(0, BOARD_WIDTH)
                .mapToObj(i -> Piece.createWhitePawn())
                .collect(Collectors.toCollection(ArrayList::new));
        this.pawns.get(WHITE_PAWN_ROW).addAll(initializedPieces);
    }

    private void initializeBlackPawns() {
        ArrayList<Piece> initializedPieces = IntStream.range(0, BOARD_WIDTH)
                .mapToObj(i -> Piece.createBlackPawn())
                .collect(Collectors.toCollection(ArrayList::new));
        this.pawns.get(BLACK_PAWN_ROW).addAll(initializedPieces);
    }

    private void validateIndex(int index) {
        if (index < 0 || index / BOARD_WIDTH >= BOARD_HEIGHT) {
            throw new IllegalArgumentException("Index out of range");
        }
    }
}
