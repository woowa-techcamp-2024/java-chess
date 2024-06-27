package pe.goblin.chess.domain.piece.vo;

public enum PieceType {
    PAWN('p', 'P', 1.0),
    ROOK('r', 'R', 5.0),
    KNIGHT('n', 'N', 2.5),
    BISHOP('b', 'B', 3.0),
    QUEEN('q', 'Q', 9.0),
    KING('k', 'K', 0.0),
    NO_PIECE('.', '.', 0.0);

    private final char whiteRepresentation;
    private final char blackRepresentation;
    private final double defaultPoint;

    PieceType(char whiteRepresentation, char blackRepresentation, double defaultPoint) {
        this.whiteRepresentation = whiteRepresentation;
        this.blackRepresentation = blackRepresentation;
        this.defaultPoint = defaultPoint;
    }

    public char getWhiteRepresentation() {
        return whiteRepresentation;
    }

    public char getBlackRepresentation() {
        return blackRepresentation;
    }

    public double getDefaultPoint() {
        return defaultPoint;
    }

    public static PieceType of(char ch) {
        for (PieceType pieceType : PieceType.values()) {
            if (pieceType.getWhiteRepresentation() == Character.toLowerCase(ch)) {
                return pieceType;
            }
        }
        throw new IllegalArgumentException("Invalid piece type: " + ch);
    }
}
