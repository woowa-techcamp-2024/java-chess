package chess.pieces;

import chess.board.Coordinate;

import java.util.List;
import java.util.Optional;

public class Pawn extends Piece {

    @Override
    public boolean verifyMoveCoordinate(Coordinate from, Coordinate to) {
        int dy = to.getRankIndex() - from.getRankIndex();
        int dx = to.getWidthIndex() - from.getWidthIndex();

        if(this.isWhite()) {
            List<Direction> directions = Direction.whitePawnDirection();
            return directions.stream()
                    .anyMatch(direction -> direction.getYDegree() == dy && direction.getXDegree() == dx);
        }
        if(this.isBlack()) {
            List<Direction> directions = Direction.blackPawnDirection();
            return directions.stream()
                    .anyMatch(direction -> direction.getYDegree() == dy && direction.getXDegree() == dx);
        }
        throw new IllegalArgumentException("폰의 색상이 잘못되었습니다.");
    }

    @Override
    public List<Coordinate> canMoveCoordinate(Coordinate from) {
        if(this.isWhite()) {
            List<Direction> directions = Direction.whitePawnDirection();

            return directions.stream()
                    .map(direction -> createCoordinate(from, direction))
                    .flatMap(Optional::stream)
                    .toList();
        }
        if(this.isBlack()) {
            List<Direction> directions = Direction.blackPawnDirection();

            return directions.stream()
                    .map(direction -> createCoordinate(from, direction))
                    .flatMap(Optional::stream)
                    .toList();
        }
        throw new IllegalArgumentException("폰의 색상이 잘못되었습니다.");
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

    public static Pawn createWhitePawn() {
        return new Pawn(Color.WHITE);
    }

    public static Pawn createBlackPawn() {
        return new Pawn(Color.BLACK);
    }

    private Pawn(Color color) {
        super(color, Type.PAWN);
    }
}
