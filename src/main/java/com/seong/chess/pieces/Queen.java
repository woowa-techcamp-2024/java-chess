package com.seong.chess.pieces;

public class Queen extends Piece {

    private static final char REPRESENTATION = 'q';
    private static final double DEFAULT_POINT = 9.0;

    private Queen(Color color) {
        super(color, REPRESENTATION, DEFAULT_POINT);
    }

    public static Queen createBlack() {
        return new Queen(Color.BLACK);
    }

    public static Queen createWhite() {
        return new Queen(Color.WHITE);
    }

    @Override
    public boolean isNotBlank() {
        return false;
    }

    @Override
    protected boolean isPiecesDirection(Direction direction) {
        return direction.isDiagonal() || direction.isRight();
    }

    @Override
    public void checkPieceCanMove(Direction direction) {
        if (isPiecesDirection(direction)) {
            return;
        }
        throw new IllegalArgumentException("퀸은 정방향, 대각선으로만 움직일 수 있습니다.");
    }
}
