package pe.goblin.chess.domain.piece;

import pe.goblin.chess.domain.board.Direction;
import pe.goblin.chess.domain.piece.vo.Color;
import pe.goblin.chess.domain.piece.vo.PieceType;

import java.util.List;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public List<Direction> getMovableDirections() {
        return Direction.linearDirection();
    }

    @Override
    public int getMovableDistance() {
        return Integer.MAX_VALUE;
    }

    @Override
    public List<Direction> getAttackableDirections() {
        return Direction.linearDirection();
    }
}
