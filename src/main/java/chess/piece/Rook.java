package chess.piece;

import chess.piece.rule.Direction;
import chess.piece.rule.PieceMove;

public class Rook extends Piece {

    private static final int MAX_DISTANCE = 8;

    private Rook(final PieceColor color) {
        super(color);
    }

    public static Rook create(final PieceColor color) {
        return new Rook(color);
    }

    @Override
    public Type getType() {
        return Type.ROOK;
    }

    @Override
    public PieceMove getMoveable() {
        return PieceMove.of(Direction.linearDirection(), MAX_DISTANCE);
    }
}
