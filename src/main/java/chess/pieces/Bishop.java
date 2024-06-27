package chess.pieces;

import chess.board.Coordinate;

public class Bishop extends Piece {


    @Override
    public boolean verifyMoveCoordinate(String source, String dest) {
        Coordinate from = Coordinate.of(source);
        Coordinate to = Coordinate.of(dest);

        int dy = to.getRankIndex() - from.getRankIndex();
        int dx = to.getWidthIndex() - from.getWidthIndex();

        // 대각선 이동인 경우
        return Math.abs(dy) == Math.abs(dx);
    }

    public static Bishop createWhiteBishop() {
        return new Bishop(Color.WHITE);
    }

    public static Bishop createBlackBishop() {
        return new Bishop(Color.BLACK);
    }

    private Bishop(Color color) {
        super(color, Type.BISHOP);
    }

}
