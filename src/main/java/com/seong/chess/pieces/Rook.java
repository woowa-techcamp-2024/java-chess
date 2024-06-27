package com.seong.chess.pieces;

public class Rook extends Piece {

    private static final char REPRESENTATION = 'r';
    private static final double DEFAULT_POINT = 5.0;

    private Rook(Color color) {
        super(Type.ROOK, color, REPRESENTATION, DEFAULT_POINT);
    }

    public static Rook createWhite() {
        return new Rook(Color.WHITE);
    }

    public static Rook createBlack() {
        return new Rook(Color.BLACK);
    }

    @Override
    public boolean isNotBlank() {
        return false;
    }

    @Override
    protected boolean isPiecesDirection(Direction direction) {
        return direction.isRight();
    }

    @Override
    public void checkPieceCanMove(Direction direction) {
        if (isPiecesDirection(direction)) {
            return;
        }
        throw new IllegalArgumentException("룩은 정방향으로만 움직일 수 있습니다.");
    }
}
