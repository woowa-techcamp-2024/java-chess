package chess.pieces;

import chess.board.Coordinate;

import java.util.List;

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
