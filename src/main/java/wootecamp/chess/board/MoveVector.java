package wootecamp.chess.board;

import wootecamp.chess.pieces.Direction;

public class MoveVector {
    private final int dx;
    private final int dy;

    public MoveVector(BoardPosition source, BoardPosition destination) {
        this.dx = destination.getFilePosition() - source.getFilePosition();
        this.dy = destination.getRankPosition() - source.getRankPosition();
    }

    public int getSquareDistance() {
        return dx * dx + dy * dy;
    }

    public double calculateInclination() {
        return (double) dy / dx;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
