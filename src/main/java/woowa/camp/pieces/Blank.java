package woowa.camp.pieces;

import static woowa.camp.pieces.Piece.Color.NONE;
import static woowa.camp.pieces.Piece.Type.NO_PIECE;

import java.util.List;
import woowa.camp.chess.Position;

public class Blank extends Piece {

    public Blank(Type type, Color color) {
        super(type, color);
    }

    public static Blank create() {
        return new Blank(NO_PIECE, NONE);
    }

    @Override
    public List<Position> findPath(final Position source) {
        return List.of();
    }

}
