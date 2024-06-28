package woowa.camp.pieces;

import static woowa.camp.pieces.Piece.Color.BLACK;
import static woowa.camp.pieces.Piece.Color.WHITE;
import static woowa.camp.pieces.Piece.Type.BISHOP;

import java.util.List;
import woowa.camp.chess.Position;

public class Bishop extends Piece {

    public Bishop(Type type, Color color) {
        super(type, color);
    }

    public static Piece createWhite() {
        return new Bishop(BISHOP, WHITE);
    }

    public static Piece createBlack() {
        return new Bishop(BISHOP, BLACK);
    }

    @Override
    public List<Position> findPath(final Position source) {

        final List<Position> positions = source.getDiagonalPositions();

        return List.of();
    }

}
