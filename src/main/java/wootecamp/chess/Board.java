package wootecamp.chess;

import wootecamp.chess.pieces.Pawn;
import wootecamp.chess.util.StringUtils;

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
    private boolean isInit = false;

    public void initialize() {
        isInit = true;
        initializeWhitePawns();
        initializeBlackPawns();
    }

    private void initializeWhitePawns() {
        whitePawns = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            whitePawns.add(new Pawn(Pawn.WHITE_COLOR));
        }
    }

    private void initializeBlackPawns() {
        blackPawns = new ArrayList<>();
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


    public String getBoardState() {
        if(!isInit) {
            throw new IllegalStateException("아직 보드가 초기화되지 않았습니다.");
        }

        StringBuilder builder = new StringBuilder();
        int finalRank = 1;
        for (int rank = BOARD_SIZE; rank > 0; rank--) {
            builder.append(getRankState(rank));
            if(rank > finalRank) {
                builder.append(StringUtils.NEWLINE);
            }
        }
        return builder.toString();
    }

    private String getRankState(int rank) {
        if(rank == WHITE_PAWN_INIT_RANK) {
            return getInitialStateWhitePawnResult();
        }
        if(rank == BLACK_PAWN_INIT_RANK) {
            return getInitialStateBlackPawnResult();
        }
        return EMPTY_RANK_MESSAGE;
    }
}
