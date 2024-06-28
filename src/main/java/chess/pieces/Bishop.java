package chess.pieces;

import chess.board.Position;

public class Bishop extends Piece{

    public Bishop(Color color, Type type) {
        super(color, type);
    }

    @Override
    public boolean verifyMovePosition(String source, String target) {
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);

        int dx = sourcePosition.getFile() - targetPosition.getFile();
        int dy = sourcePosition.getRank() - targetPosition.getRank();

        return Math.abs(dx) == Math.abs(dy);
    }

    public static Bishop createWhiteBishop() {
        return new Bishop(Color.WHITE, Type.BISHOP);
    }

    public static Bishop createBlackBishop() {
        return new Bishop(Color.BLACK, Type.BISHOP);
    }

}
