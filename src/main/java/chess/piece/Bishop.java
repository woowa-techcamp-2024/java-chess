package chess.piece;

public class Bishop extends Piece {
    public static final char WHITE_REPRESENTATION = '♗';
    public static final char BLACK_REPRESENTATION = '♝';

    private Bishop(String color, char representation) {
        super(color, representation);
    }

    public static Bishop createWhiteBishop() {
        return new Bishop(WHITE_COLOR, WHITE_REPRESENTATION);
    }

    public static Bishop createBlackBishop() {
        return new Bishop(BLACK_COLOR, BLACK_REPRESENTATION);
    }
}
