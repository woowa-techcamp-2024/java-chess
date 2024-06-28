package chess.pieces;

public class Blank extends Piece {

    public Blank() {
        super(Color.NOCOLOR, Type.NO_PIECE);
    }

    public static Blank createBlank(){
        return new Blank();
    }

    @Override
    public boolean verifyMovePosition(String source, String target) {
        return false;
    }
}
