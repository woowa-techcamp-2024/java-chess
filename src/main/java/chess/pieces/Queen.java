package chess.pieces;

import chess.board.Position;

public class Queen extends Piece{

    public Queen(Color color, Type type) {
        super(color, type);
    }

    @Override
    public boolean verifyMovePosition(String source, String target) {

        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);

        int dx = sourcePosition.getFile() - targetPosition.getFile();
        int dy = sourcePosition.getRank() - targetPosition.getRank();

        if(Math.abs(dx) == Math.abs(dy)) return true;
        if(dx == 0 || dy == 0) return true;

        return false;
    }

    public static Queen createWhiteQueen() {
        return new Queen(Color.WHITE, Type.QUEEN);
    }

    public static Queen createBlackQueen() {
        return new Queen(Color.BLACK, Type.QUEEN);
    }

}
