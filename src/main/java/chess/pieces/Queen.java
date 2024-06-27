package chess.pieces;

public class Queen extends Piece {

    @Override
    public boolean verifyMovePosition(String source, String dest) {
        return false;
    }

    public static Piece createWhiteQueen() {
        return new Queen(Color.WHITE);
    }

    public static Piece createBlackQueen() {
        return new Queen(Color.BLACK);
    }

    private Queen(Color color) {
        super(color, Type.QUEEN);
    }

}
