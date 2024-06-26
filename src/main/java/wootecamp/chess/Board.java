package wootecamp.chess;

import wootecamp.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static wootecamp.chess.util.StringUtils.appendNewline;

public class Board {
    public static int BOARD_SIZE = 8;

    private List<Rank> board = new ArrayList<>();

    public void initialize() {
        board.add(Rank.createInitialRank1());
        board.add(Rank.createInitialRank2());

        final int emptyRankCount = 4;
        for (int i = 0; i < emptyRankCount; i++) {
            board.add(Rank.createEmptyRank());
        }

        board.add(Rank.createInitialRank7());
        board.add(Rank.createInitialRank8());
    }


    public void initializeEmpty() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board.add(Rank.createEmptyRank());
        }
    }


    public int pieceCount() {
        int pieceCount = 0;
        for (Rank rank : board) {
            pieceCount += rank.pieceCount();
        }

        return pieceCount;
    }

    public int pieceCount(Piece.Color color, Piece.Type type) {
        int pieceCount = 0;
        for (Rank rank : board) {
            pieceCount += rank.pieceCount(color, type);
        }

        return pieceCount;
    }

    public String showBoard() {
        StringBuilder builder = new StringBuilder();
        for (int rank = BOARD_SIZE; rank > 0; rank--) {
            final String shownRank = board.get(rank - 1).showRank();
            builder.append(appendNewline(shownRank));
        }

        return builder.toString();
    }

    public Piece findPiece(final String position) {
        final BoardPosition boardPosition = new BoardPosition(position);
        final Rank rank = board.get(boardPosition.getRankPosition());
        return rank.findPiece(boardPosition.getFilePosition());
    }

    public void move(final String position, final Piece piece) {
        final BoardPosition boardPosition = new BoardPosition(position);
        Rank rank = board.get(boardPosition.getRankPosition());

        rank.setPiece(boardPosition.getFilePosition(), piece);
    }
}
