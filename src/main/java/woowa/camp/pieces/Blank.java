package woowa.camp.pieces;

import static woowa.camp.pieces.Piece.Color.NONE;
import static woowa.camp.pieces.Piece.Type.NO_PIECE;

public class Blank extends Piece {

    public Blank(Type type, Color color) {
        super(type, color);
    }

    public static Blank create() {
        return new Blank(NO_PIECE, NONE);
    }

}
