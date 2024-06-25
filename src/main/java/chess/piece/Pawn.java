package chess.piece;

public class Pawn extends Piece {

    public Pawn(final PieceColor pieceColor) {
        super(pieceColor);
    }

    public Type getType() {
        return Type.PAWN;
    }
}
