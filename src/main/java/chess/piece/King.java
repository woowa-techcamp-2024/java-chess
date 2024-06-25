package chess.piece;

public class King extends Piece {

    private King(final PieceColor color) {
        super(color);
    }

    public static King create(final PieceColor color) {
        return new King(color);
    }

    @Override
    public Type getType() {
        return Type.KING;
    }
}
