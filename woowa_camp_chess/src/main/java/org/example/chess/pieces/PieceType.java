package org.example.chess.pieces;

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


    public char getRepresentation() {
        return representation;
    }
}
