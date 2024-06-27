package wootecamp.chess.board;

public class MoveVector {
    private final int dx;
    private final int dy;

    public MoveVector(BoardPosition source, BoardPosition destination) {
        this.dx = Math.abs(source.getFilePosition() - destination.getFilePosition());
        this.dy = Math.abs(destination.getRankPosition() - source.getRankPosition());
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
