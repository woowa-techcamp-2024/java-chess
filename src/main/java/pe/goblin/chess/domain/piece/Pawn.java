package pe.goblin.chess.domain.piece;

import pe.goblin.chess.domain.board.Direction;
import pe.goblin.chess.domain.piece.vo.Color;
import pe.goblin.chess.domain.piece.vo.PieceType;

import java.util.List;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public List<Direction> getMovableDirections() {
        return color == Color.BLACK ? Direction.blackPawnDirection() : Direction.whitePawnDirection();
    }

    @Override
    public int getMovableDistance() {
        return 1;
    }

    @Override
    public List<Direction> getAttackableDirections() {
        return color == Color.BLACK ? Direction.blackPawnAttackDirection() : Direction.whitePawnAttackDirection();
    }
}
