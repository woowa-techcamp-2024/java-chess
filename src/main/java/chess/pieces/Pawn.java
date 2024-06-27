package chess.pieces;

import chess.Position;
import chess.constant.Color;
import chess.constant.Direction;
import chess.constant.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pawn extends Piece {
    private final List<Direction> directions;

    private Pawn(final Color color, final Position position) {
        super(color, Type.PAWN, position);
        if (isBlack()) directions = Direction.blackPawnDirection();
        else directions = Direction.whitePawnDirection();
    }

    public static Piece create(Color color, Position position) {
        return new Pawn(color, position);
    }

    @Override
    public boolean verifyMovePosition(final Piece piece) {
        List<Direction> currentDirections = new ArrayList<>(directions);
        if (this.getPosition().getY() == 1 && this.isBlack()) currentDirections.add(Direction.NORTH_TWICE);
        if (this.getPosition().getY() == 7 && this.isWhite()) currentDirections.add(Direction.SOUTH_TWICE);

        // Pawn이 이동할 수 있는 장소에 있는가?
        boolean canMovePosition = false;
        Position sourcePosition = this.getPosition();
        Position destinationPosition = piece.getPosition();
        for (Direction direction: currentDirections) {
            int y = sourcePosition.getY() + direction.getYDegree();
            int x = sourcePosition.getX() + direction.getXDegree();
            if (destinationPosition.getX() == x && destinationPosition.getY() == y) canMovePosition = true;
        }
        if (!canMovePosition) return false;

        // Pawn이 앞으로 이동하면 목적지에 빈칸이 대각선으로 이동하면 다른 기물이 있는가?
        Direction direction = getDirection(piece.getPosition());
        if (direction == Direction.SOUTH || direction == Direction.NORTH) {
            return Objects.equals(piece.getType(), Type.NO_PIECE);
        }
        else {
            return !Objects.equals(piece.getType(), Type.NO_PIECE);
        }
    }

    @Override
    public Direction getDirection(Position position) {
        return getDirection(position, directions);
    }

}
