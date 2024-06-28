package woowa.camp.pieces;

import static woowa.camp.pieces.Piece.Color.BLACK;
import static woowa.camp.pieces.Piece.Color.WHITE;
import static woowa.camp.pieces.Piece.Type.KNIGHT;

public class Knight extends Piece {

    public Knight(Type type, Color color) {
        super(type, color);
    }

    public static Piece createWhite() {
        return new Knight(KNIGHT, WHITE);
    }

    public static Piece createBlack() {
        return new Knight(KNIGHT, BLACK);
    }

}
