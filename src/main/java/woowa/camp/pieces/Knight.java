package woowa.camp.pieces;

import static woowa.camp.pieces.Piece.Color.BLACK;
import static woowa.camp.pieces.Piece.Color.WHITE;
import static woowa.camp.pieces.Piece.Type.KNIGHT;

import java.util.List;
import woowa.camp.chess.Position;

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

    @Override
    public List<Position> findPath(final Position source) {
        final List<Position> positions = source.getCrossPositions();

        // TODO:
        return List.of();
    }

}
