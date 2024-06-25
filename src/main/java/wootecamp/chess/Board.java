package wootecamp.chess;

import java.util.ArrayList;
import java.util.List;

import static wootecamp.chess.util.StringUtils.appendNewline;

public class Board {
    public static int BOARD_SIZE = 8;

    private List<Rank> board;

    public void initialize() {
        board = new ArrayList<>();

        board.add(Rank.createInitialRank1());
        board.add(Rank.createInitialRank2());

        final int EMPTY_RANK_COUNT = 4;
        for (int i = 0; i < EMPTY_RANK_COUNT; i++) {
            board.add(Rank.createEmptyRank());
        }

        board.add(Rank.createInitialRank7());
        board.add(Rank.createInitialRank8());
    }


    public int pieceCount() {
        int pieceCount = 0;
        for (Rank rank : board) {
            pieceCount += rank.pieceCount();
        }

        return pieceCount;
    }

    public String showBoard() {
        StringBuilder builder = new StringBuilder();
        for (int rank = BOARD_SIZE; rank > 0; rank--) {
            final String shownRank = getRank(rank).showRank();
            builder.append(appendNewline(shownRank));
        }

        return builder.toString();
    }

    private Rank getRank(int rank) {
        int index = rank - 1;
        return board.get(index);
    }
}
