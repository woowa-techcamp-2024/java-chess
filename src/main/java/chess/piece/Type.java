package chess.piece;

public enum Type {
    PAWN('♙', 1.0), ROOK('♖', 5.0),
    QUEEN('♕', 9.0), KING('♔', 0.0),
    KNIGHT('♘', 2.5), BISHOP('♗', 3.0),
    NO_PIECE('.', 0.0);

    private final char representation;
    private final double defaultScore;

    Type(final char representation, final double defaultScore) {
        this.representation = representation;
        this.defaultScore = defaultScore;
    }

    public char getRepresentation(final PieceColor pieceColor) {
        if (pieceColor.equals(PieceColor.BLACK)) {
            return (char) (this.representation + 6);
        } else if (pieceColor.equals(PieceColor.NO_COLOR)) {
            return NO_PIECE.representation;
        }

        return this.representation;
    }

    public char getRepresentation() {
        return this.representation;
    }

    public double getDefaultScore() {
        return this.defaultScore;
    }
}
