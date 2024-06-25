package chess.piece;

public class Queen extends Piece {
    public static final char WHITE_REPRESENTATION = '♕';
    public static final char BLACK_REPRESENTATION = '♛';
    public static final double DEFAULT_POINT = 9;

    private Queen(Color color, char representation) {
        super(color, representation);
    }

    public static Queen createWhite() {
        return new Queen(Color.WHITE, WHITE_REPRESENTATION);
    }

    public static Queen createBlack() {
        return new Queen(Color.BLACK, BLACK_REPRESENTATION);
    }

    @Override
    public double getDefaultPoint() {
        return DEFAULT_POINT;
    }
}
