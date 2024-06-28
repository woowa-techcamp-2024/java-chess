package woowa.camp.pieces;

import static woowa.camp.pieces.Piece.Color.BLACK;
import static woowa.camp.pieces.Piece.Color.WHITE;
import static woowa.camp.pieces.Piece.Type.QUEEN;

public class Queen extends Piece {

    public Queen(Type type, Color color) {
        super(type, color);
    }

    public static Piece createWhite() {
        return new Queen(QUEEN, WHITE);
    }

    public static Piece createBlack() {
        return new Queen(QUEEN, BLACK);
    }

}
