package chess.piece;

public class Knight extends Piece {
    public static final char WHITE_REPRESENTATION = '♘';
    public static final char BLACK_REPRESENTATION = '♞';
    public static final double DEFAULT_POINT = 2.5;

    private Knight(Color color, char representation) {
        super(color, representation);
    }

    public static Knight createWhiteKnight() {
        return new Knight(Color.WHITE, WHITE_REPRESENTATION);
    }

    public static Knight createBlackKnight() {
        return new Knight(Color.BLACK, BLACK_REPRESENTATION);
    }

    @Override
    public double getDefaultPoint() {
        return DEFAULT_POINT;
    }
}
