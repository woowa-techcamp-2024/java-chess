package woowa.camp.chess;

public enum BoardConstants {

    INIT_PIECE_COUNT(32),

    MAX_KING(1),
    MAX_QUEEN(1),
    MAX_ROOK(2),
    MAX_BISHOP(2),
    MAX_KNIGHT(2),
    MAX_PAWN(8),

    MAX_ROW(8),
    MAX_COL(8);

    private final int count;

    BoardConstants(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
