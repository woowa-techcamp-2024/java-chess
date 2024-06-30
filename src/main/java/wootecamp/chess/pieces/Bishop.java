package wootecamp.chess.pieces;

import wootecamp.chess.board.Direction;
import wootecamp.chess.board.MoveVector;

import java.util.Optional;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    @Override
    public boolean verifyMovePosition(MoveVector moveVector) {
        return moveVector.isDiagonalVector();
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
