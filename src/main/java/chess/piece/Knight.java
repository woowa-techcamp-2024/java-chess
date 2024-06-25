package chess.piece;

public class Knight extends Piece {
    public static final char WHITE_REPRESENTATION = '♘';
    public static final char BLACK_REPRESENTATION = '♞';

    private Knight(String color, char representation) {
        super(color, representation);
    }

    public static Knight createWhiteKnight() {
        return new Knight(WHITE_COLOR, WHITE_REPRESENTATION);
    }

    public static Knight createBlackKnight() {
        return new Knight(BLACK_COLOR, BLACK_REPRESENTATION);
    }
}
