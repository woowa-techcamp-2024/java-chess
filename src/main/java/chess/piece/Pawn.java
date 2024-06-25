package chess.piece;

public class Pawn extends Piece {

    private Pawn(final PieceColor pieceColor) {
        super(pieceColor);
    }

    public static Pawn create(final PieceColor color) {
        return new Pawn(color);
    }

    public Type getType() {
        return Type.PAWN;
    }
}
