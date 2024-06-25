package piece;

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
}
