package chess.pieces;

import chess.board.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    @Override
    public boolean verifyMoveCoordinate(Coordinate from, Coordinate to) {
        int dy = to.getRankIndex() - from.getRankIndex();
        int dx = to.getWidthIndex() - from.getWidthIndex();

        // 수평 이동인 경우
        if(dy == 0 && dx != 0) {
            return true;
        }
        // 수직 이동인 경우
        if(dy != 0 && dx == 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Coordinate> canMoveCoordinate(Coordinate from) {
        List<Direction> directions = Direction.linearDirection();

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

    public static Rook createWhiteRook() {
        return new Rook(Color.WHITE);
    }

    public static Rook createBlackRook() {
        return new Rook(Color.BLACK);
    }

    private Rook(Color color) {
        super(color, Type.ROOK);
    }

}
