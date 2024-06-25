package chess.piece;

public class Bishop extends Piece {

    private Bishop(final PieceColor color) {
        super(color);
    }

    public static Bishop create(final PieceColor color) {
        return new Bishop(color);
    }

    @Override
    public Type getType() {
        return Type.BISHOP;
    }
}
