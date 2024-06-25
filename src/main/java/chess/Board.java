package chess;

import chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Board {
    private List<List<Pawn>> board = new ArrayList<>();
    private final List<Pawn> pawns = new ArrayList<>();

    final int BOARD_SIZE = 8;
    final int INIT_WHITE_COLUMN = 6;
    final int INIT_BLACK_COLUMN = 1;

    public void add(final Pawn pawn) {
        pawns.add(pawn);
    }

    public int size() {
        return pawns.size();
    }

    public Pawn findPawn(final int index) {
        return pawns.get(index);
    }

    private void initializeNull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            List<Pawn> pawnList = new ArrayList<>();
            for (int j = 0; j < BOARD_SIZE; j++) {
                pawnList.add(null);
            }
            board.add(pawnList);
        }
    }

    private void initializePawn(final String color, final char representation, final int column) {
        List<Pawn> pawnList = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            Pawn pawn = new Pawn(color, representation);
            add(pawn);
            pawnList.add(pawn);
        }
        board.add(column, pawnList);
    }

    public void initialize() {
        initializeNull();
        initializePawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION, INIT_WHITE_COLUMN);
        initializePawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION, INIT_BLACK_COLUMN);
    }

    private String getPawnsResult(final String color) {
        StringBuilder stringBuilder = new StringBuilder();
        Stream<Pawn> pawnStream = pawns.stream().filter(pawn -> pawn.getColor().equals(color));
        pawnStream.forEach(pawn -> stringBuilder.append(pawn.getRepresentation()));
        return stringBuilder.toString();
    }

    public String getWhitePawnsResult() {
        return getPawnsResult(Pawn.WHITE_COLOR);
    }

    public String getBlackPawnsResult() {
        return getPawnsResult(Pawn.BLACK_COLOR);
    }
}
