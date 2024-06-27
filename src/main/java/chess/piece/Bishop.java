package chess.piece;

import chess.piece.rule.Direction;
import chess.piece.rule.PieceMove;

public class Bishop extends Piece {

    private static final int MAX_DISTANCE = 8;

    private Bishop(final PieceColor color) {
        super(color);
    }

    public static Bishop create(final PieceColor color) {
        return new Bishop(color);
    }

    @Override
    public Type getType() {
        return Type.BISHOP;
    }

    @Override
    public PieceMove getMoveable() {
        return PieceMove.of(Direction.diagonalDirection(), MAX_DISTANCE);
    }
}
