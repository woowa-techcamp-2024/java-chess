package wootecamp.chess.pieces;

import wootecamp.chess.board.Direction;

import java.util.List;

public class Knight extends Piece {
    private static final List<MovableDirection> movableDirections = List.of(
            new MovableDirection(1, Direction.SSE),
            new MovableDirection(1, Direction.SSW),
            new MovableDirection(1, Direction.NNW),
            new MovableDirection(1, Direction.NNE),
            new MovableDirection(1, Direction.EEN),
            new MovableDirection(1, Direction.EES),
            new MovableDirection(1, Direction.WWN),
            new MovableDirection(1, Direction.WWS)
    );

    public Knight(Color color) {
        super(color, Type.KNIGHT);
    }

    @Override
    public List<MovableDirection> getMovableDirections() {
        return movableDirections;
    }

    @Override
    public boolean canJump() {
        return true;
    }
}
