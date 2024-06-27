package wootecamp.chess.pieces;

import wootecamp.chess.board.MoveVector;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color, Type.ROOK);
    }

    @Override
    public boolean verifyMovePosition(MoveVector moveVector) {
        return false;
    }
}
