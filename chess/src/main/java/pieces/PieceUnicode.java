package pieces;

public enum PieceUnicode {
    WHITE_PAWN("\u2659"),
    WHITE_KNIGHT("\u2658"),
    WHITE_BISHOP("\u2657"),
    WHITE_ROOK("\u2656"),
    WHITE_QUEEN("\u2655"),
    WHITE_KING("\u2654"),

    BLACK_PAWN("\u265f"),
    BLACK_KNIGHT("\u265e"),
    BLACK_BISHOP("\u265d"),
    BLACK_ROOK("\u265c"),
    BLACK_QUEEN("\u265b"),
    BLACK_KING("\u265a"),
    ;
    private String unicode;

    PieceUnicode(String unicode) {
        this.unicode = unicode;
    }

    public String getUnicode() {
        return unicode;
    }
}
