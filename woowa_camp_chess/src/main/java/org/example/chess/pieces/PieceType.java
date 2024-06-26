package org.example.chess.pieces;

//todo : inner class 에 넣을 것인지 ?
public enum PieceType {
    KING('K'),
    QUEEN('Q'),
    ROOK( 'R'),
    BISHOP( 'B'),
    KNIGHT( 'N'),
    PAWN( 'P');

    private final char representation;

    PieceType(char representation) {
        this.representation = representation;
    }


    public char getBlackRepresentation() {
        return representation;
    }

    public char getWhiteRepresentation() {
        return Character.toLowerCase(representation);
    }
}
