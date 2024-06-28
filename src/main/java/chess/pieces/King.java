package chess.pieces;

import chess.board.Position;

import java.util.List;

public class King extends Piece {

    private final static List<Direction> directions = Direction.everyDirection();

    @Override
    public boolean verifyMovePosition(String source, String target) {

        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);

        int dx = targetPosition.getFile() - sourcePosition.getFile();
        int dy = targetPosition.getRank() - sourcePosition.getRank();

        for(Direction direction : directions) {
            if(Math.abs(dx) == Math.abs(direction.getXDegree()) && Math.abs(dy) == Math.abs(direction.getYDegree())) {
                return true;
            }
        }
        return false;
    }

    public King(Color color, Type type) {
        super(color, type);
    }

    public static King createWhiteKing() {
        return new King(Color.WHITE, Type.KING);
    }

    public static King createBlackKing() {
        return new King(Color.BLACK, Type.KING);
    }
}
