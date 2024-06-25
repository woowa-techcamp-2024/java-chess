package pieces;

public record Piece(PieceColor color, PieceUnicode represent) {
    public Piece() {
        this(PieceColor.WHITE, PieceUnicode.WHITE_PAWN);
    }
}
