package com.seong.chess.pieces;

public class King extends Piece {

    private static final char REPRESENTATION = 'k';
    private static final double DEFAULT_POINT = 0.0;

    private King(Color color) {
        super(Type.KING, color, REPRESENTATION, DEFAULT_POINT);
    }

    public static King createWhite() {
        return new King(Color.WHITE);
    }

    public static King createBlack() {
        return new King(Color.BLACK);
    }

    @Override
    public boolean isNotBlank() {
        return false;
    }

    @Override
    public void checkPieceCanMove(Direction direction) {
        if (direction.isRight() || direction.isDiagonal()) {
            return;
        }
        throw new IllegalArgumentException("킹은 정방향, 대각선으로만 움직일 수 있습니다.");
    }
}
