package pe.goblin.chess.domain.piece;

import pe.goblin.chess.domain.board.Direction;
import pe.goblin.chess.domain.piece.vo.Color;
import pe.goblin.chess.domain.piece.vo.PieceType;

import java.util.List;

public class Knight extends Piece{
    public Knight(Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public List<Direction> getMovableDirections() {
        return Direction.knightDirection();
    }

    @Override
    public int getMovableDistance() {
        return 1;
    }

    @Override
    public List<Direction> getAttackableDirections() {
        return Direction.knightDirection();
    }
}
