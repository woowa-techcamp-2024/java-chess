package chess.pieces;

public class Pawn extends Piece {
    private static final int WHITE_STARTING_RANK_NUMBER = 1;
    private static final int BLACK_STARTING_RANK_NUMBER = 6;

    protected Pawn(Color color) {
        super(color,
                Type.PAWN,
                color.equals(Color.WHITE) ? Direction.whitePawnDirection() : Direction.blackPawnDirection());
    }

    @Override
    Direction direction(Position src, Position target) {
        return Direction.valueOf(degree(src, target));
    }

    @Override
    protected void additionalCheck(Position src, Position target) {
        if (this.atStartingPoint(src)) {
            return;
        }
        if (degree(src, target).isOverOneYDegree()) {
            throw new IllegalArgumentException();
        }
    }

    private boolean atStartingPoint(Position position) {
        return (this.isWhite() && position.getRankNumber() == WHITE_STARTING_RANK_NUMBER) ||
                (this.isBlack() && position.getRankNumber() == BLACK_STARTING_RANK_NUMBER);

    }
}
