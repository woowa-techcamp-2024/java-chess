package chess.pieces;

import chess.board.Position;

public class Rook extends Piece {

    public Rook(Color color, Type type) {
        super(color, type);
    }

    @Override
    public boolean verifyMovePosition(String source, String target) {
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);

        int dx = targetPosition.getFile() - sourcePosition.getFile();
        int dy = targetPosition.getRank() - sourcePosition.getRank();

        return dx == 0 || dy == 0;
    }

    public static Rook createWhiteRook() {
        return new Rook(Color.WHITE, Type.ROOK);
    }

    public static Rook createBlackRook() {
        return new Rook(Color.BLACK, Type.ROOK);
    }
}
