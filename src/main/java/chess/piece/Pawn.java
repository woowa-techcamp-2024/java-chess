package chess.piece;

import chess.piece.rule.PieceMove;
import chess.piece.rule.SpecialDirection;

public class Pawn extends Piece {

    private boolean isFirstMove = true;

    private Pawn(final PieceColor pieceColor) {
        super(pieceColor);
    }

    public static Pawn create(final PieceColor color) {
        return new Pawn(color);
    }

    public Type getType() {
        return Type.PAWN;
    }

    @Override
    public PieceMove getMoveable() {
        if(isFirstMove) {
            isFirstMove = false;
            return PieceMove.of(SpecialDirection.getPawnDirection(this.color), 2);
        }

        return PieceMove.of(SpecialDirection.getPawnDirection(this.color), 1);
    }

    public void completeFirstMove() {
        isFirstMove = false;
    }

}
