package chess.pieces.enums;

public enum Symbol {
    WHITE_KING("♔"), WHITE_QUEEN("♕"), WHITE_ROOK("♖"), WHITE_BISHOP("♗"), WHITE_KNIGHT("♘"), WHITE_PAWN("♙"),
    BLACK_KING("♚"), BLACK_QUEEN("♛"), BLACK_ROOK("♜"), BLACK_BISHOP("♝"), BLACK_KNIGHT("♞"), BLACK_PAWN("♟");

    private final String value;

    Symbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
