package chess.pieces;

import chess.board.Coordinate;

import java.util.List;

public class King extends Piece {

    @Override
    public boolean verifyMoveCoordinate(String source, String dest) {
        Coordinate from = Coordinate.of(source);
        Coordinate to = Coordinate.of(dest);

        int dy = to.getRankIndex() - from.getRankIndex();
        int dx = to.getWidthIndex() - from.getWidthIndex();

        List<Direction> directions = Direction.everyDirection();

        return directions.stream()
                .anyMatch(direction -> direction.getYDegree() == dy && direction.getXDegree() == dx);
    }

    public static King createWhiteKing() {
        return new King(Color.WHITE);
    }

    public static King createBlackKing() {
       return new King(Color.BLACK);
    }

    private King(Color color) {
        super(color, Type.KING);
    }

}
