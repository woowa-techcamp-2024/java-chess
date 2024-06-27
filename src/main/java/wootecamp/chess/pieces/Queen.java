package wootecamp.chess.pieces;

import wootecamp.chess.board.MoveVector;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color, Type.QUEEN);
    }

    @Override
    public boolean verifyMovePosition(MoveVector moveVector) {
        return false;
    }
}
