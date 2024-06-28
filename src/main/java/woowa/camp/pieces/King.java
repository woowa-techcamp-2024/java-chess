package woowa.camp.pieces;

import static woowa.camp.pieces.Piece.Color.BLACK;
import static woowa.camp.pieces.Piece.Color.WHITE;
import static woowa.camp.pieces.Piece.Type.KING;

public class King extends Piece {

    public King(Type type, Color color) {
        super(type, color);
    }

    public static Piece createWhite() {
        return new King(KING, WHITE);
    }

    public static Piece createBlack() {
        return new King(KING, BLACK);
    }

}
