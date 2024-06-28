package chess.piece;

import chess.piece.rule.PieceMove;
import chess.piece.rule.SpecialDirection;

public class Knight extends Piece {

    private Knight(final PieceColor color) {
        super(color);
    }

    public static Knight create(final PieceColor color) {
        return new Knight(color);
    }

    @Override
    public Type getType() {
        return Type.KNIGHT;
    }

    @Override
    public PieceMove getMoveable() {
        return PieceMove.of(SpecialDirection.getKnightDirection(), 1);
    }
}
