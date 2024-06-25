package chess.piece;

public class Rook extends Piece {

    private Rook(final PieceColor color) {
        super(color);
    }

    public static Rook create(final PieceColor color) {
        return new Rook(color);
    }

    @Override
    public Type getType() {
        return Type.ROOK;
    }
}
