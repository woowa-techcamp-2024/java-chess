package chess.piece;

public enum Type {
    PAWN('♙'), ROOK('♖'), QUEEN('♕'),
    KING('♔'), KNIGHT('♘'), BISHOP('♗'),
    NO_PIECE('.');

    private final char representation;

    Type(final char representation) {
        this.representation = representation;
    }

    public char getRepresentation(final PieceColor pieceColor) {
        if(pieceColor.equals(PieceColor.BLACK)) {
            return (char) (this.representation + 6);
        } else if(pieceColor.equals(PieceColor.NO_COLOR)) {
            return NO_PIECE.representation;
        }

        return this.representation;
    }

    public char getRepresentation() {
        return this.representation;
    }
}
