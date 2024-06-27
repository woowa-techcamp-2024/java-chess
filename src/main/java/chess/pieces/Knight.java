package chess.pieces;

public class Knight extends Piece {


    @Override
    public boolean verifyMovePosition(String source, String dest) {
        return false;
    }

    public static Piece createWhiteKnight() {
        return new Knight(Color.WHITE);
    }

    public static Piece createBlackKnight() {
        return new Knight(Color.BLACK);
    }

    private Knight(Color color) {
        super(color, Type.KNIGHT);
    }

}
