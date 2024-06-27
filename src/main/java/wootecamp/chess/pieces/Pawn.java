package wootecamp.chess.pieces;

import wootecamp.chess.board.MoveVector;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color, Type.PAWN);
    }

    @Override
    public boolean verifyMovePosition(MoveVector moveVector) {
        return false;
    }
}
