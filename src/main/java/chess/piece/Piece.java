package chess.piece;

public abstract class Piece {

    protected final PieceColor color;

    protected Piece(final PieceColor color) {
        this.color = color;
    }

    public abstract Type getType();

    public PieceColor getColor() {
        return color;
    }
}
