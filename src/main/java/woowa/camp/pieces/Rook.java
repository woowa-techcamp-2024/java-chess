package woowa.camp.pieces;

import static woowa.camp.pieces.Piece.Color.BLACK;
import static woowa.camp.pieces.Piece.Color.WHITE;
import static woowa.camp.pieces.Piece.Type.ROOK;

public class Rook extends Piece {

    public Rook(Type type, Color color) {
        super(type, color);
    }

    public static Piece createWhite() {
        return new Rook(ROOK, WHITE);
    }

    public static Piece createBlack() {
        return new Rook(ROOK, BLACK);
    }

}
