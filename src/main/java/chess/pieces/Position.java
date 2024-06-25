package chess.pieces;

public class Position {
    private final int x, y;

    public Position(final String position) {
        char xPos = position.charAt(0);
        this.x = xPos - 'a';

        char yPos = position.charAt(1);
        this.y = 8 - Character.getNumericValue(yPos);
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
}
