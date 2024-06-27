package wootecamp.chess.pieces;

import wootecamp.chess.board.MoveVector;

public class King extends Piece {
    protected King(final Color color) {
        super(color, Type.KING);
    }

    @Override
    public boolean verifyMovePosition(MoveVector moveVector) {
        return moveVector.getSquareDistance() <= 2;
    }
}
