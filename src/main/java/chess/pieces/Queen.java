package chess.pieces;

import chess.board.Coordinate;

public class Queen extends Piece {

    @Override
    public boolean verifyMoveCoordinate(Coordinate from, Coordinate to) {
        int dy = to.getRankIndex() - from.getRankIndex();
        int dx = to.getWidthIndex() - from.getWidthIndex();

        // 대각선 이동인 경우
        if (Math.abs(dy) == Math.abs(dx)) {
            return true;
        }

        // 수평 이동인 경우
        if (dy == 0 && dx != 0) {
            return true;
        }
        // 수직 이동인 경우
        if (dy != 0 && dx == 0) {
            return true;
        }
        return false;
    }

    public static Queen createWhiteQueen() {
        return new Queen(Color.WHITE);
    }

    public static Queen createBlackQueen() {
        return new Queen(Color.BLACK);
    }

    private Queen(Color color) {
        super(color, Type.QUEEN);
    }

}
