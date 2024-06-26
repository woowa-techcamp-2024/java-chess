package chess.pieces;

public class Position {
    private final int x, y;

    public Position(final String position) {
        this.x = position.charAt(0) - 'a';
        this.y = 8 - Character.getNumericValue(position.charAt(1));
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
}
