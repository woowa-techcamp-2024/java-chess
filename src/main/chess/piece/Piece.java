package chess.piece;

public abstract class Piece {

    private final Color color;

    public Piece() {
        this(Color.WHITE);
    }

    public Piece(final Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public final String toString() {
        return getColor() == Color.WHITE ? whiteRepresentation() : blackRepresentation();
    }

    protected abstract String whiteRepresentation();

    protected abstract String blackRepresentation();

}
