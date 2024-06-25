package pieces;

public enum PieceType {
    PAWN("PAWN"),
    ROOK("ROOK"),
    KNIGHT("KNIGHT"),
    BISHOP("BISHOP"),
    QUEEN("QUEEN"),
    KING("KING"),
    NO_PIECE("NO_PIECE"),
    ;

    private String type;

    PieceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
