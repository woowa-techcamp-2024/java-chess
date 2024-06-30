package wootecamp.chess.pieces;

import wootecamp.chess.board.Board;
import wootecamp.chess.board.BoardPosition;
import wootecamp.chess.board.MoveVector;

import java.util.List;

public class EmptyPiece extends Piece {
    public EmptyPiece() {
        super(Color.EMPTY, Type.EMPTY);
    }

    @Override
    public List<BoardPosition> findAllMovablePositions(Board board, BoardPosition source) {
        throw new IllegalArgumentException("해당 칸은 비어있습니다.");
    }

    @Override
    public List<MovableDirection> getMovableDirections() {
        return List.of();
    }

    @Override
    public boolean isEmptyPiece() {
        return true;
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
