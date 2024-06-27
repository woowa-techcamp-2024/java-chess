package chess.pieces;

import chess.Position;
import chess.constant.Color;
import chess.constant.Direction;
import chess.constant.Type;

import java.util.List;
import java.util.Objects;

public abstract class Piece implements Comparable<Piece> {

    private final Color color;
    private final Type type;
    private final Position position;

    protected Piece(final Color color, final Type type, final Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
    }

    public abstract boolean verifyMovePosition(final Position position);
    public abstract Direction getDirection(final Position position);

    public Direction getDirection(Position position, List<Direction> directions) {
        Position sourcePosition = this.getPosition();

        int distance = 64;
        Direction result = null;
        for (Direction direction: directions) {
            int y = sourcePosition.getY() + direction.getYDegree();
            int x = sourcePosition.getX() + direction.getXDegree();

            int directionDistance = Math.abs(position.getX() - x) + Math.abs(position.getY() - y);
            if (distance > directionDistance) {
                distance = directionDistance;
                result = direction;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (Objects.equals(o, null)) return false;

        Piece piece = (Piece) o;
        if (!Objects.equals(this.color, piece.getColor())) return false;
        if (!Objects.equals(this.type, piece.getType())) return false;
        if (!Objects.equals(this.position, piece.getPosition())) return false;

        return true;
    }

    public Color getColor() {
        return this.color;
    }

    public Type getType() {
        return this.type;
    }

    public Position getPosition() {
        return this.position;
    }

    public boolean isWhite() { return Objects.equals(this.color, Color.WHITE); }

    public boolean isBlack() { return Objects.equals(this.color, Color.BLACK); }

    @Override
    public int compareTo(Piece piece) {
        return Double.compare(piece.getType().getDefaultPoint(), this.getType().getDefaultPoint());
    }
}