package chess.piece;

public class Rook extends Piece {
    public static final char WHITE_REPRESENTATION = '♖';
    public static final char BLACK_REPRESENTATION = '♜';
    public static final double DEFAULT_POINT = 5;

    private Rook(Color color, char representation) {
        super(color, representation);
    }

    public static Rook createWhite() {
        return new Rook(Color.WHITE, WHITE_REPRESENTATION);
    }

    public static Rook createBlack() {
        return new Rook(Color.BLACK, BLACK_REPRESENTATION);
    }

    @Override
    public double getDefaultPoint() {
        return DEFAULT_POINT;
    }
}
