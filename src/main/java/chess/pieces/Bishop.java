package chess.pieces;

public class Bishop extends Piece {

    protected Bishop(Color color) {
        super(color, Type.BISHOP, Direction.bishopDirection());
    }

    @Override
    Direction direction(Position src, Position target) {
        return Direction.valueOfDiagonal(degree(src, target));
    }
}
