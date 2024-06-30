package wootecamp.chess.pieces;

import wootecamp.chess.board.Board;
import wootecamp.chess.board.Direction;
import wootecamp.chess.board.MoveVector;

import java.util.List;
import java.util.Optional;

public class Queen extends Piece {
    private static final List<MovableDirection> movableDirections = List.of(
            new MovableDirection(Board.BOARD_SIZE, Direction.NORTH),
            new MovableDirection(Board.BOARD_SIZE, Direction.SOUTH),
            new MovableDirection(Board.BOARD_SIZE, Direction.WEST),
            new MovableDirection(Board.BOARD_SIZE, Direction.EAST),
            new MovableDirection(Board.BOARD_SIZE, Direction.NORTHWEST),
            new MovableDirection(Board.BOARD_SIZE, Direction.SOUTHEAST),
            new MovableDirection(Board.BOARD_SIZE, Direction.NORTHEAST),
            new MovableDirection(Board.BOARD_SIZE, Direction.SOUTHWEST)
    );

    public Queen(Color color) {
        super(color, Type.QUEEN);
    }

    @Override
    public List<MovableDirection> getMovableDirections() {
        return movableDirections;
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
