package wootecamp.chess.pieces;

import wootecamp.chess.board.Direction;
import wootecamp.chess.board.MoveVector;

import java.util.Optional;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color, Type.KNIGHT);
    }

    @Override
    public boolean verifyMovePosition(MoveVector moveVector) {
        return moveVector.isKnightVector();
    }

    @Override
    public boolean canJump() {
        return true;
    }
}
