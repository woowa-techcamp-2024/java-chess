package chess.pieces;

public class King extends Piece {

    @Override
    public boolean verifyMovePosition(String source, String dest) {
        return false;
    }

    public static Piece createWhiteKing() {
        return new King(Color.WHITE);
    }

    public static Piece createBlackKing() {
       return new King(Color.BLACK);
    }

    private King(Color color) {
        super(color, Type.KING);
    }

}
