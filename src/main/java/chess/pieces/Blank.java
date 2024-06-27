package chess.pieces;

public class Blank extends Piece {

    @Override
    public boolean verifyMovePosition(String source, String dest) {
        return false;
    }

    public static Piece createBlank() {
        return new Blank(Color.NOCOLOR);
    }

    private Blank(Color color) {
        super(color, Type.NO_PIECE);
    }

}
