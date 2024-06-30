package wootecamp.chess.pieces;

import wootecamp.chess.board.MoveVector;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color, Type.PAWN);
    }

    @Override
    public boolean verifyMovePosition(MoveVector moveVector) {
        return Direction.determineDirection(moveVector).filter(it -> {
                    if (color == Color.WHITE) {
                        return verifyWhiteMovePosition(moveVector, it);
                    }
                    if (color == Color.BLACK) {
                        return verifyBlackMovePosition(moveVector, it);
                    }
                    return false;
                }
        ).isPresent();
    }

    @Override
    public boolean canJump() {
        return false;
    }

    //TODO: 더 가독성이 좋아질 수 있도록 수정
    private boolean verifyWhiteMovePosition(MoveVector moveVector, Direction direction) {
        if (direction == Direction.SOUTH && moveVector.getSquareDistance() <= 4) {
            return true;
        }
        if (direction == Direction.SOUTHWEST || direction == Direction.SOUTHEAST && moveVector.getSquareDistance() == 2) {
            return true;
        }
        return false;
    }

    private boolean verifyBlackMovePosition(MoveVector moveVector, Direction direction) {
        if (direction == Direction.NORTH && moveVector.getSquareDistance() <= 4) {
            return true;
        }
        if (direction == Direction.NORTHWEST || direction == Direction.NORTHEAST && moveVector.getSquareDistance() == 2) {
            return true;
        }
        return false;
    }
}
