package org.example.chess.pieces;

//todo : inner class 에 넣을 것인지 ?
public enum PieceType {
    KING('K', 1.0),
    QUEEN('Q', 9.0),
    ROOK( 'R', 5.0),
    BISHOP( 'B', 3.0),
    KNIGHT( 'N', 2.5),
    PAWN( 'P', 1.0),
    NO_PIECE('.', 0.0);

    private final char representation;
    private final double defaultPoint;

    PieceType(char representation, double defaultPoint) {
        this.representation = representation;
        this.defaultPoint = defaultPoint;
    }

    public double getDefaultPoint() {
        return defaultPoint;
    }

    public char getRepresentation() {
        return representation;
    }

    public char getBlackRepresentation() {
        return representation;
    }

    public char getWhiteRepresentation() {
        return Character.toLowerCase(representation);
    }
}
