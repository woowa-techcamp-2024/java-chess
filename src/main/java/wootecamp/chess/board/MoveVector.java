package wootecamp.chess.board;

import java.util.Optional;

public class MoveVector {
    private final int dx;
    private final int dy;

    public MoveVector(BoardPosition source, BoardPosition destination) {
        this.dx = destination.getFilePosition() - source.getFilePosition();
        this.dy = destination.getRankPosition() - source.getRankPosition();
    }

    public double calculateInclination() {
        return (double) dy / dx;
    }

    public int calculateSquareDistance() {
        return dx * dx + dy * dy;
    }

    public Optional<Direction> findDirection() {
        if (isKnightVector()) {
            return determineNightDirection();
        }
        if (isLinearVector()) {
            return determineLinearDirection();
        }
        if (isDiagonalVector()) {
            return determineDiagonal();
        }

        return Optional.empty();
    }

    public boolean isKnightVector() {
        final int knightDistance = 5;
        return calculateSquareDistance() == knightDistance;
    }

    public boolean isLinearVector() {
        return dx == 0 || dy == 0;
    }

    public boolean isDiagonalVector() {
        return Math.abs(calculateInclination()) == 1.0;
    }

    private Optional<Direction> determineNightDirection() {
        return Direction.knightDirection().stream()
                .filter(direction -> direction.getX() == dx && direction.getY() == dy)
                .findAny();
    }

    private Optional<Direction> determineLinearDirection() {
        return Direction.linearDirection().stream()
                .filter(this::matchDirection)
                .findAny();
    }

    private Optional<Direction> determineDiagonal() {
        return Direction.diagonalDirection().stream()
                .filter(this::matchDirection)
                .findAny();
    }

    private int calculateDirectionX() {
        if(dx == 0) {
            return 0;
        }
        return dx / Math.abs(dx);
    }

    private int calculateDirectionY() {
        if(dy == 0) {
            return 0;
        }
        return dy / Math.abs(dy);
    }

    private boolean matchDirection(final Direction direction) {
        return direction.getX() == calculateDirectionX() && direction.getY() == calculateDirectionY();
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
