package chess.pieces;

public class Bishop extends Piece {


    @Override
    public boolean verifyMovePosition(String source, String dest) {
        return false;
    }

    public static Piece createWhiteBishop() {
        return new Bishop(Color.WHITE);
    }

    public static Piece createBlackBishop() {
        return new Bishop(Color.BLACK);
    }

    private Bishop(Color color) {
        super(color, Type.BISHOP);
    }


}
