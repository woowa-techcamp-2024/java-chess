package wootecamp.chess.board;

import wootecamp.chess.pieces.Direction;

public class MoveVector {
    private final int dx;
    private final int dy;

    public MoveVector(BoardPosition source, BoardPosition destination) {
        this.dx = source.getFilePosition() - destination.getFilePosition();
        this.dy = source.getRankPosition() - destination.getRankPosition();
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
