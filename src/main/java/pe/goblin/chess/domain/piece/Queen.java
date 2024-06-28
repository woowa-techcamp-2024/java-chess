package pe.goblin.chess.domain.piece;

import pe.goblin.chess.domain.board.Direction;
import pe.goblin.chess.domain.piece.vo.Color;
import pe.goblin.chess.domain.piece.vo.PieceType;

import java.util.List;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public List<Direction> getMovableDirections() {
        return Direction.everyDirection();
    }

    @Override
    public int getMovableDistance() {
        return Integer.MAX_VALUE;
    }

    @Override
    public List<Direction> getAttackableDirections() {
        return Direction.everyDirection();
    }
}
