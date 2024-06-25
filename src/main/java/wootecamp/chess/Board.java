package wootecamp.chess;

import wootecamp.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Board {
    private static final int BOARD_SIZE = 8;
    private static final int WHITE_PAWN_INIT_RANK = 2;
    private static final int BLACK_PAWN_INIT_RANK = 7;

    private static final String EMPTY_RANK_MESSAGE = "........";


    private List<Pawn> whitePawns = new ArrayList<>();
    private List<Pawn> blackPawns = new ArrayList<>();

    public void initialize() {
        initializeWhitePawns();
        initializeBlackPawns();
    }

    private void initializeWhitePawns() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            whitePawns.add(new Pawn(Pawn.WHITE_COLOR));
        }
    }

    private void initializeBlackPawns() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            blackPawns.add(new Pawn(Pawn.BLACK_COLOR));
        }
    }

    public String getInitialStateWhitePawnResult() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            result.append(whitePawns.get(i).getRepresentation());
        }
        return result.toString();
    }

    public String getInitialStateBlackPawnResult() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            result.append(blackPawns.get(i).getRepresentation());
        }
        return result.toString();
    }
}
