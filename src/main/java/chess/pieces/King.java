package chess.pieces;

import chess.Position;
import chess.constant.Color;
import chess.constant.Direction;
import chess.constant.Type;

import java.util.List;

public class King extends Piece {
    private final List<Direction> directions = Direction.everyDirection();
    private King(final Color color, final Position position) { super(color, Type.KING, position); }

    public static Piece create(Color color, Position position) {
        return new King(color, position);
    }

    @Override
    public boolean verifyMovePosition(final Piece piece) {
        Position sourcePosition = this.getPosition();
        Position destinationPosition = piece.getPosition();

        for (Direction direction: directions) {
            int y = sourcePosition.getY() + direction.getYDegree();
            int x = sourcePosition.getX() + direction.getXDegree();
            if (destinationPosition.getX() == x && destinationPosition.getY() == y) return true;
        }
        return false;
    }

    @Override
    public Direction getDirection(Position position) {
        return getDirection(position, directions);
    }
}
