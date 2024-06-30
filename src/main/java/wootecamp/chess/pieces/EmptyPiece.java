package wootecamp.chess.pieces;

import wootecamp.chess.board.MoveVector;

public class EmptyPiece extends Piece {
    public EmptyPiece() {
        super(Color.EMPTY, Type.EMPTY);
    }

    @Override
    public boolean verifyMovePosition(MoveVector moveVector) {
        throw new IllegalArgumentException("비어있는 위치입니다.");
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
