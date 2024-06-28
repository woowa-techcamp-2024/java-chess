package woowa.camp.pieces;

import static woowa.camp.pieces.Piece.Color.BLACK;
import static woowa.camp.pieces.Piece.Color.WHITE;
import static woowa.camp.pieces.Piece.Type.PAWN;

import java.util.ArrayList;
import java.util.List;
import woowa.camp.chess.Position;

public class Pawn extends Piece {

    private int moveCount;

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

    @Override
    public List<Position> findPath(final Position source) {

        final List<Position> positions = new ArrayList<>();

        if (getColor() == BLACK) {

            if (moveCount == 0) {
            }

            return positions;
        }

        return positions;
    }

}
