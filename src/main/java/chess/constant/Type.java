package chess.constant;

public enum Type {
    PAWN('p', 1.0),
    ROOK('r', 5.0),
    KNIGHT('n', 2.5),
    BISHOP('b', 3.0),
    QUEEN('q', 9.0),
    KING('k', 0.0),
    NO_PIECE('.', 0.0);

    private final char representation;
    private final double defaultPoint;

    Type(char representation, double defaultPoint) {
        this.representation = representation;
        this.defaultPoint = defaultPoint;
    }

    public char getRepresentation() {
        return this.representation;
    }

    public char getWhiteRepresentation() {
        return this.representation;
    }

    public char getBlackRepresentation() {
        return Character.toUpperCase(this.representation);
    }

    public double getDefaultPoint() {
        return this.defaultPoint;
    }
}
