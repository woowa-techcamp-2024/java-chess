package wootecamp.chess.pieces;

import wootecamp.chess.board.Direction;

import java.util.List;

public class King extends Piece {
    private static final List<MovableDirection> movableDirections = List.of(
            new MovableDirection(1, Direction.NORTHEAST),
            new MovableDirection(1, Direction.NORTHWEST),
            new MovableDirection(1, Direction.SOUTHEAST),
            new MovableDirection(1, Direction.SOUTHWEST),
            new MovableDirection(1, Direction.NORTH),
            new MovableDirection(1, Direction.SOUTH),
            new MovableDirection(1, Direction.WEST),
            new MovableDirection(1, Direction.EAST)
    );

    protected King(final Color color) {
        super(color, Type.KING);
    }

    //todo : 체크, 체크메이트 구현

    @Override
    public List<MovableDirection> getMovableDirections() {
        return movableDirections;
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
