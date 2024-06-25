package chess.piece;

public class Knight extends Piece {

    private Knight(final PieceColor color) {
        super(color);
    }

    public static Knight create(final PieceColor color) {
        return new Knight(color);
    }

    @Override
    public Type getType() {
        return Type.KNIGHT;
    }
}
