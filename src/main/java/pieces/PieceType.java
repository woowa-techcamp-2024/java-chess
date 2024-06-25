package pieces;

public enum PieceType {

    PAWN("pawn", 'P'),
    KNIGHT("knight", 'N'),
    ROOK("rook", 'R'),
    BISHOP("bishop", 'B'),
    QUEEN("queen", 'Q'),
    KING("king", 'K'),
    BLANK("blank", '.');

    private final String name;
    private final char representation;

    PieceType(String name, char representation) {
        this.name = name;
        this.representation = representation;
    }

    public String getName() {
        return name;
    }

    public char getRepresentation() {
        return representation;
    }
}
