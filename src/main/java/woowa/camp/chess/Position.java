package woowa.camp.chess;

import static woowa.camp.chess.BoardConstants.MAX_COL;
import static woowa.camp.chess.BoardConstants.MAX_ROW;

public class Position {

    private final int row;
    private final int col;

    public Position(int row, int col) {
        validate(row, col);
        this.row = row;
        this.col = col;
    }

    public static Position mapBy(final String position) {
        char colPos = position.charAt(0);
        char rowPos = position.charAt(1);
        validate(rowPos, colPos);

        int col = colPos - 'a';
        int row = (MAX_ROW.getCount() - Character.getNumericValue(rowPos)) % MAX_ROW.getCount();
        return new Position(row, col);
    }

    private static void validate(final char rowPos, final char colPos) {
        if (isInRange(rowPos, colPos)) {
            return;
        }
        throw new IllegalArgumentException(String.format("잘못된 체스 위치입니다. {%c, %c}", rowPos, colPos));
    }

    private static boolean isInRange(final char row, final char col) {
        return ('1' <= row && row <= '8') && ('a' <= col && col <= 'h');
    }

    private void validate(final int row, final int col) {
        if (isInRange(row, col)) {
            return;
        }
        throw new IllegalArgumentException(String.format("잘못된 체스 위치입니다. {%d, %d}", row, col));
    }

    private boolean isInRange(final int row, final int col) {
        return (0 <= row && row < MAX_ROW.getCount()) && (0 <= col && col < MAX_COL.getCount());
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
