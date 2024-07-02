package chess.pieces;

import chess.board.Coordinate;

import java.util.List;
import java.util.Optional;

public class King extends Piece {

    @Override
    public boolean verifyMoveCoordinate(Coordinate from, Coordinate to) {
        int dy = to.getRankIndex() - from.getRankIndex();
        int dx = to.getWidthIndex() - from.getWidthIndex();

        List<Direction> directions = Direction.everyDirection();

        return directions.stream()
                .anyMatch(direction -> direction.getYDegree() == dy && direction.getXDegree() == dx);
    }

    @Override
    public List<Coordinate> canMoveCoordinate(Coordinate from) {
        List<Direction> directions = Direction.everyDirection();

        return directions.stream()
                .map(direction -> createCoordinate(from, direction))
                .flatMap(Optional::stream)
                .toList();
    }

    private Optional<Coordinate> createCoordinate(Coordinate from, Direction direction) {
        int dy = direction.getYDegree() + from.getRankIndex();
        int dx = direction.getXDegree() + from.getWidthIndex();
        try {
            return Optional.of(Coordinate.of(dy, dx));
        } catch (Exception e) {
            return Optional.empty();
        }
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
