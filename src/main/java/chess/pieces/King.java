package chess.pieces;

import chess.exception.InvalidMovePositionException;
import chess.pieces.Position.Degree;

public class King extends Piece {

    protected King(Color color) {
        super(color, Type.KING, Direction.kingDirection());
    }

    @Override
    Direction direction(Position src, Position target) {
        return Direction.valueOf(degree(src, target));
    }

    @Override
    protected void additionalCheck(Position src, Position target) {
        Degree degree = degree(src, target);
        if (degree.isOverOneYDegree() || degree.isOverOneXDegree()) {
            throw new InvalidMovePositionException();
        }
    }
}
