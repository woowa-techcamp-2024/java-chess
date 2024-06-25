package chess.piece;

public class Pawn extends Piece {
    public static final char WHITE_REPRESENTATION = '♙';
    public static final char BLACK_REPRESENTATION = '♟';
    public static final double DEFAULT_POINT = 1;

    private Pawn(Color color, char representation) {
        super(color, representation);
    }

    public static Pawn createWhite() {
        return new Pawn(Color.WHITE, WHITE_REPRESENTATION);
    }

    public static Pawn createBlack() {
        return new Pawn(Color.BLACK, BLACK_REPRESENTATION);
    }

    @Override
    public double getDefaultPoint() {
        return DEFAULT_POINT;
    }
}
