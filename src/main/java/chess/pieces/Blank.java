package chess.pieces;

public class Blank extends Piece {

    @Override
    public boolean verifyMoveCoordinate(String source, String dest) {
        return false;
    }

    public static Blank createBlank() {
        return new Blank(Color.NOCOLOR);
    }

    private Blank(Color color) {
        super(color, Type.NO_PIECE);
    }

}
