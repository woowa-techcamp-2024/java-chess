package woowa.camp.pieces;

import static woowa.camp.pieces.Piece.Color.BLACK;
import static woowa.camp.pieces.Piece.Color.WHITE;
import static woowa.camp.pieces.Piece.Type.KING;

import java.util.List;
import woowa.camp.chess.Position;

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

    @Override
    public List<Position> findPath(final Position source) {

        final List<Position> positions = source.getSurroundingPositions();

        // TODO:

        return List.of();
    }

}
