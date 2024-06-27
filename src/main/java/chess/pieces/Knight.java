package chess.pieces;

public class Knight extends Piece {

    protected Knight(Color color) {
        super(color, Type.KNIGHT, Direction.knightDirection());
    }

    @Override
    Direction direction(Position src, Position target) {
        return Direction.valueOf(degree(src, target));
    }
}
