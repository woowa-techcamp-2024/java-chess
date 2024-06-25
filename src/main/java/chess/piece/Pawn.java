package chess.piece;

public class Pawn extends Piece {
    public static final char WHITE_REPRESENTATION = '♙';
    public static final char BLACK_REPRESENTATION = '♟';

    private Pawn(String color, char representation) {
        super(color, representation);
    }

    public static Pawn createWhitePawn() {
        return new Pawn(WHITE_COLOR, WHITE_REPRESENTATION);
    }

    public static Pawn createBlackPawn() {
        return new Pawn(BLACK_COLOR, BLACK_REPRESENTATION);
    }
}
