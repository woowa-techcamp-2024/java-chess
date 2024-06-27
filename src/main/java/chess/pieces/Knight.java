package chess.pieces;

import chess.board.Coordinate;

import java.util.List;

public class Knight extends Piece {


    @Override
    public boolean verifyMoveCoordinate(String source, String dest) {
        Coordinate from = Coordinate.of(source);
        Coordinate to = Coordinate.of(dest);

        int dy = to.getRankIndex() - from.getRankIndex();
        int dx = to.getWidthIndex() - from.getWidthIndex();

        List<Direction> directions = Direction.knightDirection();

        return directions.stream()
                .anyMatch(direction -> direction.getYDegree() == dy && direction.getXDegree() == dx);
    }

    public static Knight createWhiteKnight() {
        return new Knight(Color.WHITE);
    }

    public static Knight createBlackKnight() {
        return new Knight(Color.BLACK);
    }

    private Knight(Color color) {
        super(color, Type.KNIGHT);
    }

}
