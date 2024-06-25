package pieces;

public record Piece(PieceColor color) {
    public Piece() {
        this(PieceColor.WHITE);
    }
}
