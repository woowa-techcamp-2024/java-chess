package woowa.camp.pieces;

import static woowa.camp.pieces.Piece.Color.BLACK;
import static woowa.camp.pieces.Piece.Color.WHITE;
import static woowa.camp.pieces.Piece.Type.ROOK;

import java.util.List;
import woowa.camp.chess.Position;

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

    @Override
    public List<Position> findPath(final Position source) {

        final List<Position> positions = source.getHorizontalAndVerticalPositions();
        // TODO:

        return List.of();
    }

}
