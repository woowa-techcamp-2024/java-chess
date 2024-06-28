package pe.goblin.chess.domain.piece;

import pe.goblin.chess.domain.board.Direction;
import pe.goblin.chess.domain.piece.vo.Color;
import pe.goblin.chess.domain.piece.vo.PieceType;

import java.util.List;

public class King extends Piece{
    public King(Color color) {
        super(color, PieceType.KING);
    }

    @Override
    public List<Direction> getMovableDirections() {
        return Direction.everyDirection();
    }

    @Override
    public int getMovableDistance() {
        return 1;
    }

    @Override
    public List<Direction> getAttackableDirections() {
        return Direction.everyDirection();
    }
}
