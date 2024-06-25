package chess.piece;

public class King extends Piece {
    public static final char WHITE_REPRESENTATION = '♔';
    public static final char BLACK_REPRESENTATION = '♚';
    public static final double DEFAULT_POINT = 0;

    private King(Color color, char representation) {
        super(color, representation);
    }

    public static King createWhite() {
        return new King(Color.WHITE, WHITE_REPRESENTATION);
    }

    public static King createBlack() {
        return new King(Color.BLACK, BLACK_REPRESENTATION);
    }

    @Override
    public double getDefaultPoint() {
        return DEFAULT_POINT;
    }
}
