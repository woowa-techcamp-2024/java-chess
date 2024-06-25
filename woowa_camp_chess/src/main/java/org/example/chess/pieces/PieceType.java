package org.example.chess.pieces;

public enum PieceType {
    KING("King", 'K'),
    QUEEN("Queen", 'Q'),
    ROOK("Rook", 'R'),
    BISHOP("Bishop", 'B'),
    KNIGHT("Knight", 'N'),
    PAWN("Pawn", 'P');

    private final String fullName;
    private final char abbreviation;

    PieceType(String fullName, char abbreviation) {
        this.fullName = fullName;
        this.abbreviation = abbreviation;
    }

    public String getFullName() {
        return fullName;
    }

    public char getAbbreviation() {
        return abbreviation;
    }
}
