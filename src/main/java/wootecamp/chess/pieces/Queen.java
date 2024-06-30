package wootecamp.chess.pieces;

import wootecamp.chess.board.Direction;
import wootecamp.chess.board.MoveVector;

import java.util.Optional;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color, Type.QUEEN);
    }

    @Override
    public boolean verifyMovePosition(MoveVector moveVector) {
        return moveVector.isLinearVector() || moveVector.isDiagonalVector();
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
