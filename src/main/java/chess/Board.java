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

    public void initialize() {
        initializeNull();

        List<Pawn> whitePawns = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            Pawn pawn = new Pawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
            add(pawn);
            whitePawns.add(pawn);
        }
        board.add(INIT_WHITE_COLUMN, whitePawns);

        List<Pawn> blackPawns = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            Pawn pawn = new Pawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
            add(pawn);
            blackPawns.add(pawn);
        }
        board.add(INIT_BLACK_COLUMN, blackPawns);
    }

    public String getWhitePawnsResult() {
        StringBuilder stringBuilder = new StringBuilder();
        Stream<Pawn> whitePawns = pawns.stream().filter(pawn -> pawn.getColor().equals(Pawn.WHITE_COLOR));
        whitePawns.forEach(pawn -> stringBuilder.append(pawn.getRepresentation()));
        return stringBuilder.toString();
    }

    public String getBlackPawnsResult() {
        StringBuilder stringBuilder = new StringBuilder();
        Stream<Pawn> blackPawns = pawns.stream().filter(pawn -> pawn.getColor().equals(Pawn.BLACK_COLOR));
        blackPawns.forEach(pawn -> stringBuilder.append(pawn.getRepresentation()));
        return stringBuilder.toString();
    }
}
