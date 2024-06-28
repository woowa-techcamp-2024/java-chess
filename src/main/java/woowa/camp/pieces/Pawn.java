package woowa.camp.pieces;

import static woowa.camp.pieces.Piece.Color.BLACK;
import static woowa.camp.pieces.Piece.Color.WHITE;
import static woowa.camp.pieces.Piece.Type.PAWN;

public class Pawn extends Piece {

    public Pawn(Type type, Color color) {
        super(type, color);
    }

    public static Piece create(final Color color) {
        return new Pawn(PAWN, color);
    }

    public static Piece createWhite() {
        return new Pawn(PAWN, WHITE);
    }

    public static Piece createBlack() {
        return new Pawn(PAWN, BLACK);
    }

}
