package pieces;

public enum PieceUnicode {
    WHITE_PAWN("♙"),
    WHITE_KNIGHT("♘"),
    WHITE_BISHOP("♗"),
    WHITE_ROOK("♖"),
    WHITE_QUEEN("♕"),
    WHITE_KING("♔"),

    BLACK_PAWN("♟"),
    BLACK_KNIGHT("♞"),
    BLACK_BISHOP("♝"),
    BLACK_ROOK("♜"),
    BLACK_QUEEN("♛"),
    BLACK_KING("♚"),

    BLANK("."),
    ;
    private String unicode;

    PieceUnicode(String unicode) {
        this.unicode = unicode;
    }

    public String getUnicode() {
        return unicode;
    }
}
