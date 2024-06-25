package pieces;

public record Piece(PieceColor color, PieceType type, PieceUnicode represent) {
    public boolean isBlack()
    {
        return color.equals(PieceColor.BLACK);
    }
    public boolean isWhite()
    {
        return color.equals(PieceColor.WHITE);
    }
}
