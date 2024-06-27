package chess.pieces;

public class Rook extends Piece {

    @Override
    public boolean verifyMovePosition(String source, String dest) {
        return false;
    }

    public static Piece createWhiteRook() {
        return new Rook(Color.WHITE);
    }

    public static Piece createBlackRook() {
        return new Rook(Color.BLACK);
    }

    private Rook(Color color) {
        super(color, Type.ROOK);
    }

}
