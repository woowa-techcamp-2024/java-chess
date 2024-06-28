package pe.goblin.chess.domain.piece;

import pe.goblin.chess.domain.board.Direction;
import pe.goblin.chess.domain.piece.vo.Color;
import pe.goblin.chess.domain.piece.vo.PieceType;

import java.util.List;

public class Bishop extends Piece{
    public Bishop(Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public List<Direction> getMovableDirections() {
        return Direction.diagonalDirection();
    }

    @Override
    public int getMovableDistance() {
        return Integer.MAX_VALUE;
    }

    @Override
    public List<Direction> getAttackableDirections() {
        return Direction.diagonalDirection();
    }
}
