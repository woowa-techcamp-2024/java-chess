package chess.piece;

public abstract class Piece {

    private final Color color;

    protected Piece(final Color color) {
        this.color = color;
    }

    public boolean isBlack() {
        return isColor(Color.BLACK);
    }

    public boolean isWhite() {
        return isColor(Color.WHITE);
    }

    public boolean isColor(Color color) {
        return this.color == color;
    }

    public abstract double value();

    @Override
    public final String toString() {
        return isWhite() ? whiteRepresentation() : blackRepresentation();
    }

    protected abstract String whiteRepresentation();

    protected abstract String blackRepresentation();

    public enum Color {
        BLACK, WHITE
    }

}
