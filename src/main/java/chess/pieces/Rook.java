package chess.pieces;

import chess.board.Coordinate;

public class Rook extends Piece {

    @Override
    public boolean verifyMoveCoordinate(Coordinate from, Coordinate to) {
        int dy = to.getRankIndex() - from.getRankIndex();
        int dx = to.getWidthIndex() - from.getWidthIndex();

        // 수평 이동인 경우
        if(dy == 0 && dx != 0) {
            return true;
        }
        // 수직 이동인 경우
        if(dy != 0 && dx == 0) {
            return true;
        }
        return false;
    }

    public static Rook createWhiteRook() {
        return new Rook(Color.WHITE);
    }

    public static Rook createBlackRook() {
        return new Rook(Color.BLACK);
    }

    private Rook(Color color) {
        super(color, Type.ROOK);
    }

}
