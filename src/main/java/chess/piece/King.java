package chess.piece;

public class King extends Piece {
    public static final char WHITE_REPRESENTATION = '♔';
    public static final char BLACK_REPRESENTATION = '♚';

    private King(String color, char representation) {
        super(color, representation);
    }

    public static King createWhiteKing() {
        return new King(WHITE_COLOR, WHITE_REPRESENTATION);
    }

    public static King createBlackKing() {
        return new King(BLACK_COLOR, BLACK_REPRESENTATION);
    }
}
