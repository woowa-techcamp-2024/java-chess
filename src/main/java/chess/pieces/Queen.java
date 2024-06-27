package chess.pieces;

public class Queen extends Piece {
    protected Queen(Color color) {
        super(color, Type.QUEEN, Direction.queenDirection());
    }

    @Override
    Direction direction(Position src, Position target) {
        return Direction.valueOfLEvery(degree(src, target));
    }
}
