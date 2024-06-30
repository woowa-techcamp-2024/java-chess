package wootecamp.chess.pieces;

import wootecamp.chess.board.Direction;
import wootecamp.chess.board.MoveVector;

import java.util.Optional;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color, Type.ROOK);
    }

    @Override
    public boolean verifyMovePosition(MoveVector moveVector) {
        return moveVector.isLinearVector();
    }

    @Override
    public boolean canJump() {
        return false;
    }
}