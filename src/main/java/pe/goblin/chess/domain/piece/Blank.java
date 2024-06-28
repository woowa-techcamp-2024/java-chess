package pe.goblin.chess.domain.piece;

import pe.goblin.chess.domain.board.Direction;
import pe.goblin.chess.domain.piece.vo.Color;
import pe.goblin.chess.domain.piece.vo.PieceType;

import java.util.Collections;
import java.util.List;

public class Blank extends Piece {
    public Blank() {
        super(Color.NOCOLOR, PieceType.NO_PIECE);
    }

    @Override
    public List<Direction> getMovableDirections() {
        return Collections.emptyList();
    }

    @Override
    public int getMovableDistance() {
        return 0;
    }

    @Override
    public List<Direction> getAttackableDirections() {
        return Collections.emptyList();
    }
}
