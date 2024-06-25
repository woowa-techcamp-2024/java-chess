package pieces;

public record Piece(PieceColor color, PieceUnicode represent) {
    public Piece() {
        this(PieceColor.WHITE, PieceUnicode.WHITE_PAWN);
    }

    public boolean isBlack()
    {
        return color.equals(PieceColor.BLACK);
    }
    public boolean isWhite()
    {
        return color.equals(PieceColor.WHITE);
    }
}
