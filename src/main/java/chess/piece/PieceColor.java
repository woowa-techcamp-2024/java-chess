package chess.piece;

public enum PieceColor {
    WHITE, BLACK, NO_COLOR;

    public PieceColor flip() {
        return this == WHITE ? BLACK : WHITE;
    }
}
