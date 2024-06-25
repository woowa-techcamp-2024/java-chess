package chess.piece;

public class Queen extends Piece {
    public static final char WHITE_REPRESENTATION = '♕';
    public static final char BLACK_REPRESENTATION = '♛';

    private Queen(String color, char representation) {
        super(color, representation);
    }

    public static Queen createWhiteQueen() {
        return new Queen(WHITE_COLOR, WHITE_REPRESENTATION);
    }

    public static Queen createBlackQueen() {
        return new Queen(BLACK_COLOR, BLACK_REPRESENTATION);
    }
}
