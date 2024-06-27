package chess.pieces;

import chess.Position;
import chess.constant.Color;
import chess.constant.Direction;
import chess.constant.Type;

import java.util.List;

public class Queen extends Piece {
    private final List<Direction> directions = Direction.everyDirection();

    private Queen(final Color color, final Position position) { super(color, Type.QUEEN, position); }

    public static Piece create(Color color, Position position) {
        return new Queen(color, position);
    }

    @Override
    public boolean verifyMovePosition(final Piece piece) {
        Position sourcePosition = this.getPosition();
        Position destinationPosition = piece.getPosition();

        for (Direction direction: directions) {
            Position nextPosition = new Position(sourcePosition.getX() + direction.getXDegree(), sourcePosition.getY() + direction.getYDegree());
            boolean moveAvailable = moveVirtualQueen(nextPosition, destinationPosition, direction.getYDegree(), direction.getXDegree());
            if (moveAvailable) return true;
        }
        return false;
    }

    @Override
    public Direction getDirection(Position position) {
        return getDirection(position, directions);
    }

    private boolean moveVirtualQueen(final Position source, final Position destination, final int y_grad, final int x_grad) {
        if (source.isOutOfIndex()) return false;
        if (source.getX() == destination.getX() && source.getY() == destination.getY()) return true;

        Position position = new Position(source.getX() + x_grad, source.getY() + y_grad);
        return moveVirtualQueen(position, destination, y_grad, x_grad);
    }
}
