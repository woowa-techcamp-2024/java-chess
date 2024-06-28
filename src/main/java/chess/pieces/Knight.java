package chess.pieces;

import chess.board.Position;

import java.util.List;

public class Knight extends Piece{

    private final static List<Direction> directions = Direction.knightDirection();

    public Knight(Color color, Type type) {
        super(color, type);
    }

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

    public static Knight createWhiteKnight() {
        return new Knight(Color.WHITE, Type.KNIGHT);
    }

    public static Knight createBlackKnight() {
        return new Knight(Color.BLACK, Type.KNIGHT);
    }


}
