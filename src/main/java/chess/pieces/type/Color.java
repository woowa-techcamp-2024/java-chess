package chess.pieces.type;

public enum Color {
    WHITE, BLACK, NOCOLOR;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean hasColor() {
        return this != NOCOLOR;
    }
}
