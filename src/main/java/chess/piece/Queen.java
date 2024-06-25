package chess.piece;

public class Queen extends Piece {

    private Queen(final PieceColor color) {
        super(color);
    }

    public static Queen create(final PieceColor color) {
        return new Queen(color);
    }

    @Override
    public Type getType() {
        return Type.QUEEN;
    }
}
