package pieces;

public record Piece(PieceColor color, PieceType type) {
    public boolean isBlack()
    {
        return color.equals(PieceColor.BLACK);
    }
    public boolean isWhite()
    {
        return color.equals(PieceColor.WHITE);
    }

    public String getRepresent(){
        return PieceDescriptor.getUnicode(color, type).getUnicode();
    }
}
