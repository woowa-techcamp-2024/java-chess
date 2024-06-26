package chess.pieces;

import static chess.Board.BOARD_SIZE;

public class Position {
    private final int x, y;

    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Position(final String position) {
        this.x = position.charAt(0) - 'a';
        this.y = 8 - Character.getNumericValue(position.charAt(1));
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public boolean isOutOfIndex() {
        return !((0 <= x && x < BOARD_SIZE) && (0 <= y && y < BOARD_SIZE));
    }
}
