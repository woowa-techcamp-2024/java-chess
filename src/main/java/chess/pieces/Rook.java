package chess.pieces;

public class Rook extends Piece {

    protected Rook(Color color) {
        super(color, Type.ROOK, Direction.rookDirection());
    }

    @Override
    Direction direction(Position src, Position target) {
        return Direction.valueOfLinear(degree(src, target));
    }
}
