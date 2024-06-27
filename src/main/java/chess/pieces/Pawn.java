package chess.pieces;

public class Pawn extends Piece {

    @Override
    public boolean verifyMovePosition(String source, String dest) {
        return false;
    }

    public static Piece createWhitePawn() {
        return new Pawn(Color.WHITE);
    }

    public static Piece createBlackPawn() {
        return new Pawn(Color.BLACK);
    }

    private Pawn(Color color) {
        super(color, Type.PAWN);
    }
}
