package chess.pieces;

import chess.board.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {


    @Override
    public boolean verifyMoveCoordinate(Coordinate from, Coordinate to) {

        int dy = to.getRankIndex() - from.getRankIndex();
        int dx = to.getWidthIndex() - from.getWidthIndex();

        // 대각선 이동인 경우
        return Math.abs(dy) == Math.abs(dx);
    }

    @Override
    public List<Coordinate> canMoveCoordinate(Coordinate from) {
        List<Direction> directions = Direction.diagonalDirection();

        List<Coordinate> canMoveCoordinates = new ArrayList<>();
        for (Direction direction : directions) {
            int count = 1;
            while(true) {
                int dy = (direction.getYDegree() * count) + from.getRankIndex();
                int dx = (direction.getXDegree() * count) + from.getWidthIndex();
                try {
                    Coordinate coordinate = Coordinate.of(dy, dx);
                    canMoveCoordinates.add(coordinate);
                    count++;
                }
                catch(Exception e) {
                    break;
                }
            }
        }
        return canMoveCoordinates;
    }



    public static Bishop createWhiteBishop() {
        return new Bishop(Color.WHITE);
    }

    public static Bishop createBlackBishop() {
        return new Bishop(Color.BLACK);
    }

    private Bishop(Color color) {
        super(color, Type.BISHOP);
    }

}
