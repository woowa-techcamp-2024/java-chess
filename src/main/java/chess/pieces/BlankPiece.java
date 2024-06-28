package chess.pieces;

import chess.exception.InvalidMovePositionException;
import java.util.List;

public class BlankPiece extends Piece {

    protected BlankPiece() {
        super(Color.NO_COLOR, Type.NO_PIECE, List.of());
    }

    @Override
    Direction direction(Position src, Position target) {
        return null;
    }

    @Override
    protected void additionalCheck(Position src, Position target) {
        throw new InvalidMovePositionException();
    }
}
