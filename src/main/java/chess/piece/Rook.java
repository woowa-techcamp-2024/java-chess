package chess.piece;

public class Rook extends Piece {
    public static final char WHITE_REPRESENTATION = '♖';
    public static final char BLACK_REPRESENTATION = '♜';

    private Rook(String color, char representation) {
        super(color, representation);
    }

    public static Rook createWhiteRook() {
        return new Rook(WHITE_COLOR, WHITE_REPRESENTATION);
    }

    public static Rook createBlackRook() {
        return new Rook(BLACK_COLOR, BLACK_REPRESENTATION);
    }
}
